package com.spring.hard.controleur;

import org.apache.tika.metadata.Metadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.spring.hard.DTO.SaveMovieDTO;
import com.spring.hard.function.Function;
import com.spring.hard.models.Film;
import com.spring.hard.service.FilmService;
import com.spring.hard.service.VideoMetadataExtractor;

import org.springframework.http.ResponseEntity;

import java.io.RandomAccessFile;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;

@RestController
@RequestMapping("/film")
public class FilmControleur {
    @Autowired
    private FilmService service;
    public final VideoMetadataExtractor extractor= new VideoMetadataExtractor();

    public static String path="/home/hardy/Vidéos/NEW/";

    @CrossOrigin(origins = "*")
    @GetMapping("/all")
    public ResponseEntity<List<Film>> getAllFilmApi() {
        List<Film> entities = service.getAll();
        return new ResponseEntity<>(entities, HttpStatus.OK);
    }

    @GetMapping("/liste")
    public ResponseEntity<?> getAllFilm(){
        try {
            List<Film> films=service.getAll();
            return new ResponseEntity<>(films,HttpStatus.OK);
        } catch (Exception e) {
            return ResponseEntity.status(501).body(e);
        }
    }

    @GetMapping(value = "/stream/{id}/{token}")
    @ResponseBody
    public ResponseEntity<StreamingResponseBody> playMediaV01(@RequestHeader(value = "Range", required = false)
    String rangeHeader,@PathVariable long id,@PathVariable String token)
    {
        Film film=null;
        try {
            film = service.estAbonne(token, id);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
        }
        String filePathString = path+film.getCategorie().getNom()+"/"+film.getIdFilm()+"-"+film.getNom()+" ("+film.getAnnee()+").mp4";

        try
        {
            StreamingResponseBody responseStream;
            Path filePath = Paths.get(filePathString);
            Long fileSize = Files.size(filePath);
            byte[] buffer = new byte[1024];      
            final HttpHeaders responseHeaders = new HttpHeaders();
            if (rangeHeader == null)
            {
                responseHeaders.add("Content-Type", "video/mp4");
                responseHeaders.add("Content-Length", fileSize.toString());
                responseStream = os -> {
                    RandomAccessFile file = new RandomAccessFile(filePathString, "r");
                    try (file)
                    {
                    long pos = 0;
                    file.seek(pos);
                    while (pos < fileSize - 1)
                    {                            
                        file.read(buffer);
                        os.write(buffer);
                        pos += buffer.length;
                    }
                    os.flush();
                    } catch (Exception e) {}
                };
                
                return new ResponseEntity<StreamingResponseBody>
                        (responseStream, responseHeaders, HttpStatus.OK);
            }

            String[] ranges = rangeHeader.split("-");
            Long rangeStart = Long.parseLong(ranges[0].substring(6));
            Long rangeEnd;
            if (ranges.length > 1)
            {
                rangeEnd = Long.parseLong(ranges[1]);
            }
            else
            {
                rangeEnd = fileSize - 1;
            }
                
            if (fileSize < rangeEnd)
            {
                rangeEnd = fileSize - 1;
            }

            String contentLength = String.valueOf((rangeEnd - rangeStart) + 1);
            responseHeaders.add("Content-Type", "video/mp4");
            responseHeaders.add("Content-Length", contentLength);
            responseHeaders.add("Accept-Ranges", "bytes");
            responseHeaders.add("Content-Range", "bytes" + " " + 
                                rangeStart + "-" + rangeEnd + "/" + fileSize);
            final Long _rangeEnd = rangeEnd;
            responseStream = os -> {
                RandomAccessFile file = new RandomAccessFile(filePathString, "r");
                try (file)
                {
                    long pos = rangeStart;
                    file.seek(pos);
                    while (pos < _rangeEnd)
                    {                        
                    file.read(buffer);
                    os.write(buffer);
                    pos += buffer.length;
                    }
                    os.flush();
                }
                catch (Exception e) {}
            };
                
            return new ResponseEntity<StreamingResponseBody>
                    (responseStream, responseHeaders, HttpStatus.PARTIAL_CONTENT);
        }
        catch (Exception e)
        {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    
    @GetMapping("/recherche/{nom}/{page}/{taille}")
    public ResponseEntity<?> rechercheParNom(@PathVariable String nom,@PathVariable int page,@PathVariable int taille){
        nom=nom.trim();
        Page<Film> pageFilm=service.rechecheParNom(nom, page, taille);
        System.out.println(pageFilm.getContent().size());
        return ResponseEntity.ok(pageFilm.getContent());
    }

    @GetMapping("/liste/{page}/{taille}")
    public ResponseEntity<?> getAllPaginate(@PathVariable(name = "page") int page,@PathVariable(name = "taille") int taille){
        Page<Film> pageFilm=service.getAll(page, taille, null);
        return ResponseEntity.ok(pageFilm.getContent());
    }

    @PostMapping("/save")
    public ResponseEntity<?> saveMovie(@RequestPart SaveMovieDTO dto,@RequestPart MultipartFile fichier){
        try {
            Metadata metadata = extractor.extractMetadata(fichier);
            String duration = metadata.get("xmpDM:duration");
            Film film=new Film();
            film.setAnnee(dto.getAnnee());
            film.setDure((int)Double.parseDouble(duration)/60);
            film.setImage(dto.getImage());
            film.setNom(dto.getNom());
            film.setSynopsis(dto.getSysnopsis());
            film.setDateAjout(Function.getCurrenDate());
            film.setCategorie(dto.getCategorie());            
            service.saveOrUpdate(film);
            service.saveFile(fichier,film);
            return ResponseEntity.ok(film);
        } catch (Exception e) {
            return ResponseEntity.status(502).body(e);
        }
    }

    @PostMapping("/delete") 
    public ResponseEntity<?> deleteMovie(@RequestBody Film film){
        film.setIsDelete(1);
        service.saveOrUpdate(film);
        return ResponseEntity.ok("delete fait");
    }   

}