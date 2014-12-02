/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.nakexammgmt.persistence.entity;

import javax.persistence.*;

/**
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class ExamPerformanceProtocolItem extends AbstractEntity{

    private ExamPerformance oldExamPerformance;
    private ExamPerformance newExamPerformance;
    private Employee editor;
    private Type type;

    @OneToOne(optional = false)
    public ExamPerformance getOldExamPerformance() {
        return oldExamPerformance;
    }

    public void setOldExamPerformance(ExamPerformance oldExamPerformance) {
        this.oldExamPerformance = oldExamPerformance;
    }

    @OneToOne
    public ExamPerformance getNewExamPerformance() {
        return newExamPerformance;
    }

    public void setNewExamPerformance(ExamPerformance newExamPerformance) {
        this.newExamPerformance = newExamPerformance;
    }

    @ManyToOne(optional = false)
    public Employee getEditor() {
        return editor;
    }

    public void setEditor(Employee editor) {
        this.editor = editor;
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, updatable = false)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public static enum Type {

        EDIT,
        REVERSAL;

    }
}
