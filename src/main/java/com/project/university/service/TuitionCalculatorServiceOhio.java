/**
 * 
 */
package com.project.university.service;

import java.util.List;


import org.springframework.stereotype.Service;

import com.project.university.domain.*;


@Service("tutionOhio")
public class TuitionCalculatorServiceOhio implements TuitionCalculatorService {

	public double computeTutition(Student student, List<Course> listOfCourseByStudent) {

		int sumOfUnits = 0;
		double tutionCost = 0;

		for (int i = 0; i < listOfCourseByStudent.size(); i++) {
			if (listOfCourseByStudent.get(i).getDeptName().equalsIgnoreCase("Chemistry")) {

				sumOfUnits = sumOfUnits + (listOfCourseByStudent.get(i).getUnits() + 50);
			} else {
				sumOfUnits = sumOfUnits + listOfCourseByStudent.get(i).getUnits();
			}
		}
		if (student.isGraduate()) {
			tutionCost = sumOfUnits * 120;
		} else {
			tutionCost = sumOfUnits * 100;
		}

		if (student.isInternational()) {
			double additionIntCost = tutionCost / 10;
			tutionCost = tutionCost + additionIntCost;
		}

		return tutionCost;
	}

}
