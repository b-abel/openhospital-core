package org.isf.examination.manager;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import org.isf.examination.model.PatientExamination;
import org.isf.examination.service.ExaminationOperations;
import org.isf.generaldata.ExaminationParameters;
import org.isf.patient.model.Patient;
import org.isf.utils.exception.OHServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExaminationBrowserManager {
	
	@Autowired
	private ExaminationOperations ioOperations;

	/**
	 * Default PatientExamination
	 */
	public PatientExamination getDefaultPatientExamination(Patient patient){
		PatientExamination defaultPatient = new PatientExamination(
				new Timestamp(new Date().getTime()), 
				patient, 
				new Integer(ExaminationParameters.HEIGHT_INIT), 
				new Double(ExaminationParameters.WEIGHT_INIT), 
				new Integer(ExaminationParameters.AP_MIN_INIT), 
				new Integer(ExaminationParameters.AP_MAX_INIT),
				new Integer(ExaminationParameters.HR_INIT), 
				new Double(ExaminationParameters.TEMP_INIT), 
				new Double(ExaminationParameters.SAT_INIT), 
				"");
		return defaultPatient;
	}

	/**
	 * Get from last PatientExamination (only height, weight & note)
	 */
	public PatientExamination getFromLastPatientExamination(PatientExamination lastPatientExamination){
		PatientExamination newPatientExamination = new PatientExamination(new Timestamp(new Date().getTime()), lastPatientExamination.getPatient(), lastPatientExamination.getPex_height(),
				lastPatientExamination.getPex_weight(), lastPatientExamination.getPex_ap_min(), lastPatientExamination.getPex_ap_max(), lastPatientExamination.getPex_hr(), 
				lastPatientExamination.getPex_temp(), lastPatientExamination.getPex_sat(), lastPatientExamination.getPex_note());
		return newPatientExamination;
	}

	/**
	 * @param path - the PatientHistory to save
	 * @throws OHServiceException 
	 */
	public void saveOrUpdate(PatientExamination patex) throws OHServiceException {
        ioOperations.saveOrUpdate(patex);
	}

	public PatientExamination getByID(int id) throws OHServiceException{
        return ioOperations.getByID(id);
	}

	public PatientExamination getLastByPatID(int patID) throws OHServiceException {
		ArrayList<PatientExamination> patExamination = getByPatID(patID);
		
		return !patExamination.isEmpty() ? patExamination.get(0) : null;
	}

	public ArrayList<PatientExamination> getLastNByPatID(int patID, int number) throws OHServiceException {
        return ioOperations.getLastNByPatID(patID, number);
	}

	public ArrayList<PatientExamination> getByPatID(int patID) throws OHServiceException {
        return ioOperations.getByPatID(patID);
	}
	
	/**
	 * @param patexList - the {@link PatientExamination} to delete.
	 * @throws OHServiceException 
	 */
	public void remove(ArrayList<PatientExamination> patexList) throws OHServiceException {
		ioOperations.remove(patexList);
	}

}
