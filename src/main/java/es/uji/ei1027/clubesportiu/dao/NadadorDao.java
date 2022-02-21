package es.uji.ei1027.clubesportiu.dao;

import es.uji.ei1027.clubesportiu.model.Nadador;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;


@Repository // En Spring els DAOs van anotats amb @Repository
public class NadadorDao {

    private JdbcTemplate jdbcTemplate;

    // Obté el jdbcTemplate a partir del Data Source
    @Autowired
    public void setDataSource(DataSource dataSource) {
        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    /* Afegeix el nadador a la base de dades */
    public void addNadador(Nadador nadador) {
        jdbcTemplate.update("INSERT INTO Nadador VALUES(?, ?, ?, ?, ?)",
                nadador.getNom(), nadador.getNumFederat(), nadador.getPais(), nadador.getEdat(), nadador.getGenere());
    }

    /* Esborra el nadador de la base de dades */
    public void deleteNadador(Nadador nadador) {
        jdbcTemplate.update("DELETE FROM Nadador WHERE nom = '" + nadador.getNom() + "'");
    }

    /* Esborra el nadador de la base de dades POR NOMBRE */
    public void deleteNadador(String nom) {
        jdbcTemplate.update("DELETE FROM Nadador WHERE nom = '" + nom + "'");
    }

    /* Actualitza els atributs del nadador
       (excepte el nom, que és la clau primària) */
    public void updateNadador(Nadador nadador) {
        jdbcTemplate.update("UPDATE Nadador SET num_federat = '" + nadador.getNumFederat() +
                                "', edat = " + nadador.getEdat() +
                                ",pais = '" + nadador.getPais()+
                                "', genere = '" +nadador.getGenere() +
                                "' WHERE nom ='" + nadador.getNom()+"'");
    }

    /* Obté el nadador amb el nom donat. Torna null si no existeix. */
    public Nadador getNadador(String nomNadador) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM Nadador WHERE nom = '" + nomNadador + "'",
                    new NadadorRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return null;
        }
    }


    /* Obté tots els nadadors. Torna una llista buida si no n'hi ha cap. */
    public List<Nadador> getNadadors() {
        try {
            return jdbcTemplate.query("SELECT * FROM Nadador", new NadadorRowMapper());
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Nadador>();
        }
    }

    /* Obtiene lista de los nadadores de un pais. */
    public List<Nadador> getNadadorsPais(String pais) {
        try {
            return jdbcTemplate.query("SELECT * FROM Nadador WHERE pais=?",
                    new NadadorRowMapper(), pais);
        }
        catch(EmptyResultDataAccessException e) {
            return new ArrayList<Nadador>();
        }
    }
}

