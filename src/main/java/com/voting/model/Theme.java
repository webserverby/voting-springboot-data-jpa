package com.voting.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Voting theme
 *
 * @author Artem Faenko
 */
@Entity
@Table(name="theme")
public class Theme implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name="link", nullable=true)
    private String link;

    @Column(name="name", nullable=true)
    private String name;

    @Column(name="createDate", nullable=true)
    @Temporal(TemporalType.DATE)
    private Date createDate;

    @Column(name="closeDate", nullable=true)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date closeDate;

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "theme", cascade = CascadeType.ALL)
    private Set<Question> questions = new HashSet<Question>();

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    public Theme(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Theme theme = (Theme) o;

        if (id != null ? !id.equals(theme.id) : theme.id != null) return false;
        if (link != null ? !link.equals(theme.link) : theme.link != null) return false;
        if (name != null ? !name.equals(theme.name) : theme.name != null) return false;
        if (createDate != null ? !createDate.equals(theme.createDate) : theme.createDate != null) return false;
        return closeDate != null ? closeDate.equals(theme.closeDate) : theme.closeDate == null;

    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (link != null ? link.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (createDate != null ? createDate.hashCode() : 0);
        result = 31 * result + (closeDate != null ? closeDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Theme{" +
                "id=" + id +
                ", link='" + link + '\'' +
                ", name='" + name + '\'' +
                ", createDate=" + createDate +
                ", closeDate=" + closeDate +
                '}';
    }
}
