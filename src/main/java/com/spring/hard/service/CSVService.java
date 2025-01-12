package com.spring.hard.service;

import org.springframework.stereotype.Service;

@Service
public class CSVService {    
    // public void csvToBaseEtape(MultipartFile csv) throws CsvException {
    //     List<EtapeCsv> data = Function.importCSVEtape(csv);
        
    //     String insertQuery = "INSERT INTO etapes (nom,longueur,nombre_equipe,rang,temps_depart,id_course) VALUES (?, ?, ?, ?, ?, ?)";
        
    //     jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
    //         @Override
    //         public void setValues(PreparedStatement ps, int i) throws SQLException {
    //             EtapeCsv ligne = data.get(i);
    //             ps.setString(1, ligne.getEtape());
    //             ps.setDouble(2, ligne.getLongueur());
    //             ps.setDouble(3, ligne.getNbCoureur());
    //             ps.setInt(4, ligne.getRang());
    //             Timestamp depart=Timestamp.from(ligne.getDepart().toInstant());
    //             ps.setTimestamp(5, depart);
    //             ps.setInt(6, 2);
    //         }
    //         @Override
    //         public int getBatchSize() {
    //             return data.size();
    //         }
    //     });
    // }

    // public void csvToBasePoint(MultipartFile csv) throws CsvException {
    //     List<PointCsv> data = Function.importCSVPoint(csv);
        
    //     String insertQuery = "INSERT INTO point (classement,point) VALUES (?, ?)";
        
    //     jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
    //         @Override
    //         public void setValues(PreparedStatement ps, int i) throws SQLException {
    //             PointCsv ligne = data.get(i);
    //             ps.setInt(1, ligne.getClassement());
    //             ps.setInt(2, ligne.getPoint());
    //         }
    //         @Override
    //         public int getBatchSize() {
    //             return data.size();
    //         }
    //     });
    // }

    // public void csvDevisToBaseResultat(MultipartFile csv) throws CsvException {
        
    //     String tableName = "tempResultatCSV";
    //     List<ResultatCsv> data = Function.importCSVResultat(csv);
    
    //     String createTableQuery = "CREATE TABLE IF NOT EXISTS " + tableName + " ("
    //             + "etape_rang int,"
    //             + "numero_dossard int,"
    //             + "nom VARCHAR(100),"
    //             + "genre VARCHAR(5),"
    //             + "date_naissance date,"
    //             + "equipe VARCHAR(50),"
    //             + "arrivee timestamp(6)"
    //             + ")";
    //     jdbcTemplate.update(createTableQuery);
    
    //     String insertQuery = "INSERT INTO " + tableName + " (etape_rang, numero_dossard, nom, genre, date_naissance, equipe, arrivee) VALUES (?, ?, ?, ?, ?, ?, ?)";
        
    //     jdbcTemplate.batchUpdate(insertQuery, new BatchPreparedStatementSetter() {
    //         @Override
    //         public void setValues(PreparedStatement ps, int i) throws SQLException {
    //             ResultatCsv ligne = data.get(i);
    //             ps.setInt(1, ligne.getEtape_rang());
    //             ps.setInt(2, ligne.getDossard());
    //             ps.setString(3, ligne.getNom());
    //             ps.setString(4, ligne.getGenre());
    //             ps.setDate(5, ligne.getNaissance());
    //             ps.setString(6, ligne.getEquipe());
    //             Timestamp arrive=Timestamp.from(ligne.getArrive().toInstant());
    //             ps.setTimestamp(7,arrive);
    //         }
    //         @Override
    //         public int getBatchSize() {
    //             return data.size();
    //         }
    //     });
    
    //     String clientInsert = "INSERT INTO equipe (authentification, is_admin,mot_de_passe) "
    //             + "SELECT DISTINCT equipe,0,equipe FROM " + tableName;
    //     jdbcTemplate.execute(clientInsert);
    
    //     String finitionInsert = "INSERT INTO coureur (dossard,nom,genre,naissance,id_equipe) "+
    //         "SELECT DISTINCT "+
    //         "numero_dossard,nom,genre,date_naissance,id_equipe "+
    //         "FROM "+tableName+" "+
    //         "JOIN equipe ON "+tableName+".equipe=equipe.authentification";
    //     jdbcTemplate.execute(finitionInsert);
        
    //     String demandeInsert = "INSERT INTO resultat (temps_arrive,id_coureur,id_etape,id_equipe) SELECT "+
    //     "arrivee,coureur.id_coureur,id_etape,coureur.id_equipe "+
    //     "from "+tableName+" "+
    //     "join coureur on "+tableName+".numero_dossard=coureur.dossard "+
    //     "join etapes on "+tableName+".etape_rang=etapes.rang";
    //     jdbcTemplate.execute(demandeInsert);

    //     jdbcTemplate.execute("drop table "+tableName);

    // }


}
