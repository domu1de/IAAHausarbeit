/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.persistence.entity;

import javax.persistence.*;

/**
 * Entity to store protocol items for ExamPerformances.
 * All updates in ExamPerformances will be documented with such an item.
 * The entity provides an inner type enum to differentiate between edits and reversals.
 * Because of reversals the reference to the new ExamPerformance is optional.
 *
 * @author Alexander Mersmann <alexander.mersmann@nordakademie.de>
 */
@Entity
public class ExamPerformanceProtocolItem extends AbstractEntity{

    private ExamPerformance oldExamPerformance;
    private ExamPerformance newExamPerformance;
    private Employee editor;
    private Type type;

    @OneToOne(optional = false)
    @JoinColumn(unique = true, updatable = false, nullable = false)
    public ExamPerformance getOldExamPerformance() {
        return oldExamPerformance;
    }

    public void setOldExamPerformance(ExamPerformance oldExamPerformance) {
        this.oldExamPerformance = oldExamPerformance;
    }

    @OneToOne(optional = true)
    @JoinColumn(unique = true, updatable = false, nullable = true)
    public ExamPerformance getNewExamPerformance() {
        return newExamPerformance;
    }

    public void setNewExamPerformance(ExamPerformance newExamPerformance) {
        this.newExamPerformance = newExamPerformance;
    }

    @ManyToOne(optional = false)
    @JoinColumn(updatable = false, nullable = false)
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

    /**
     * Equals by: oldExamPerformance.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExamPerformanceProtocolItem that = (ExamPerformanceProtocolItem) o;

        if (oldExamPerformance != null ? !oldExamPerformance.equals(that.oldExamPerformance) : that.oldExamPerformance != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        return oldExamPerformance != null ? oldExamPerformance.hashCode() : 0;
    }

    public static enum Type {

        EDIT,
        REVERSAL;

    }
}
