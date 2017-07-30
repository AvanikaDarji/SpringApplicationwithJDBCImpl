
package com.project.university.service;

import java.util.List;


import com.project.university.domain.*;


public interface TuitionCalculatorService {

	public double computeTutition(Student student,List<Course> listOfCourseByStudent );
}
