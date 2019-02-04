package br.ufjf.model;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author Rian Alves
 */
public class Repositorio {

    private long id;
    private String fullName;
    private String name;
    private String url;
    private String description;
    private String linguagem;
    private Integer forks;
    private Integer size;
    private Integer star;
    private Integer subscribers;
    private Integer watchers;
    private String constituinte;

    public Repositorio(long id, String fullName, String name, String url, String description, String linguagem, int forks, int size, int star, int subscribers, int watchers, String constituinte) {
        this.id = id;
        this.fullName = fullName;
        this.name = name;
        this.url = url;
        this.description = description;
        this.linguagem = linguagem;
        this.forks = forks;
        this.size = size;
        this.star = star;
        this.subscribers = subscribers;
        this.watchers = watchers;
        this.constituinte = constituinte;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getLinguagem() {
        return linguagem;
    }

    /**
     * @param linguagem the linguagem to set
     */
    public void setLinguagem(String linguagem) {
        this.linguagem = linguagem;
    }

    /**
     * @return the forks
     */
    public Integer getForks() {
        return forks;
    }

    /**
     * @param forks the forks to set
     */
    public void setForks(int forks) {
        this.forks = forks;
    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the star
     */
    public Integer getStar() {
        return star;
    }

    /**
     * @param star the star to set
     */
    public void setStar(int star) {
        this.star = star;
    }

    /**
     * @return the subscribers
     */
    public Integer getSubscribers() {
        return subscribers;
    }

    /**
     * @param subscribers the subscribers to set
     */
    public void setSubscribers(int subscribers) {
        this.subscribers = subscribers;
    }

    /**
     * @return the watchers
     */
    public Integer getWatchers() {
        return watchers;
    }

    /**
     * @param watchers the watchers to set
     */
    public void setWatchers(int watchers) {
        this.watchers = watchers;
    }

    /**
     * @return the constituinte
     */
    public String getConstituinte() {
        return constituinte;
    }

    /**
     * @param constituinte the constituinte to set
     */
    public void setConstituinte(String constituinte) {
        this.constituinte = constituinte;
    }

    public Double getMedia() {

        BigDecimal a = new BigDecimal((this.forks + this.star + this.watchers) / 3.0);
        BigDecimal roundOff = a.setScale(2, BigDecimal.ROUND_HALF_EVEN);
        return roundOff.doubleValue();
    }
}
