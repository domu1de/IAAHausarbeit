/*
 * Copyright (c) 2014 Domenic Muskulus, Alexander Mersmann.
 * Licensed under the MIT License.
 */

package de.nak.exammgmt.presentation.action;

import de.nak.exammgmt.persistence.entity.Exam;
import de.nak.exammgmt.presentation.action.interceptor.Protected;
import de.nak.exammgmt.presentation.model.WelcomeAdminActionModel;
import de.nak.exammgmt.presentation.model.WelcomeManagementActionModel;
import de.nak.exammgmt.service.CourseService;
import de.nak.exammgmt.service.ExamService;
import de.nak.exammgmt.service.ManipleService;
import de.nak.exammgmt.service.StudentService;
import de.nak.exammgmt.service.home.AdminService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.SortedMap;

/**
 * Action that is mapped to the root URL of the application and provides different landing pages for the three roles
 * of the application.
 *
 * @author Domenic Muskulus <domenic@muskulus.eu>
 */
@Protected(login = true)
public class WelcomeAction extends BaseAction {

    private static final String ADMIN = "admin";
    private static final String STUDENT = "input.student";
    private static final String LECTURER = "lecturer";
    private static final String MANAGEMENT = "management";

    private AdminService adminService;
    private ExamService examService;
    private ManipleService manipleService;
    private StudentService studentService;
    private CourseService courseService;

    private WelcomeAdminActionModel welcomeAdminActionModel;
    private WelcomeManagementActionModel welcomeManagementActionModel;

    @Override
    public String execute() throws Exception {
        switch (getCurrentUser().getRole().getName()) {
            case ADMIN:
                executeAdmin();
                return ADMIN;
            case STUDENT:
                executeStudent();
                return STUDENT;
            case LECTURER:
                executeLecturer();
                return LECTURER;
            case MANAGEMENT:
                executeManagement();
                return MANAGEMENT;
            default:
                return LOGIN; // TODO permission for login  and here exception
        }
    }

    private void executeManagement() {
        welcomeManagementActionModel = new WelcomeManagementActionModel();

        SortedMap<LocalDate, List<Exam>> map = welcomeManagementActionModel.getExams();
        List<Exam> exams = examService.list();
        exams.sort(Comparator.comparing(e -> e.getCourse().getCourseId()));
        for (Exam exam : exams) {
            List<Exam> list = map.computeIfAbsent(exam.getDate(), (key) -> new ArrayList<>());
            list.add(exam);
        }

        welcomeManagementActionModel.setStudents(studentService.list());
        welcomeManagementActionModel.setManiples(manipleService.list());
        welcomeManagementActionModel.setCourses(courseService.list());
    }

    private void executeLecturer() {
        //TODO implement.
    }

    private void executeStudent() {
        //TODO check if student assigned.
    }

    private void executeAdmin() {
        welcomeAdminActionModel = new WelcomeAdminActionModel();
        welcomeAdminActionModel.setUsers(adminService.listUsers());
    }

    public void setAdminService(AdminService adminService) {
        this.adminService = adminService;
    }

    public void setExamService(ExamService examService) {
        this.examService = examService;
    }

    public WelcomeAdminActionModel getWelcomeAdminActionModel() {
        return welcomeAdminActionModel;
    }

    public void setWelcomeAdminActionModel(WelcomeAdminActionModel welcomeAdminActionModel) {
        this.welcomeAdminActionModel = welcomeAdminActionModel;
    }

    public WelcomeManagementActionModel getWelcomeManagementActionModel() {
        return welcomeManagementActionModel;
    }

    public void setWelcomeManagementActionModel(WelcomeManagementActionModel welcomeManagementActionModel) {
        this.welcomeManagementActionModel = welcomeManagementActionModel;
    }

    public void setManipleService(ManipleService manipleService) {
        this.manipleService = manipleService;
    }

    public void setStudentService(StudentService studentService) {
        this.studentService = studentService;
    }

    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }
}
