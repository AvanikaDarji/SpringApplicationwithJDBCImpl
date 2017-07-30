/**

 * 
 */
package com.project.university.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.university.domain.*;



@Service("tutionNational")
public class TuitionCalculatorServiceNational implements TuitionCalculatorService {

	public double computeTutition(Student student, List<Course> listOfCourseByStudent) {

		int sumOfUnits = 0;
		double tutionCost = 0;

		for (int i = 0; i < listOfCourseByStudent.size(); i++) {

			sumOfUnits = sumOfUnits + listOfCourseByStudent.get(i).getUnits();

		}

		if (student.isInternational()) {

			tutionCost = sumOfUnits * 500;
		} else {
			tutionCost = sumOfUnits * 230;
		}

		return tutionCost;
	}

}
