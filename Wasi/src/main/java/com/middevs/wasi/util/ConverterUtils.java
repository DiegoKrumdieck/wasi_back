package com.middevs.wasi.util;

import java.util.HashMap;
import java.util.Map;

import com.middevs.wasi.dto.IBMWatsonDTO;
import com.middevs.wasi.dto.InputDTOAzure;

public class ConverterUtils {

	private static Map<String, String> mapaGenero = new HashMap<String,String>(){{
	    this.put("Masculino", "1");
	    this.put("Femenino", "2");
	}};

	private static Map<String, String> mapaMaritalState = new HashMap<String,String>(){{
	    this.put("Soltero", "1");
	    this.put("Casado", "2");
	    this.put("Conviviendo", "3");
	    this.put("Viudo", "4");
	    this.put("Divorciado", "5");
	}};
	
	private static Map<String, String> mapaProfesion = new HashMap<String,String>(){{
	    this.put("Estudiante", "1");
	    this.put("Ingeniería", "2");
	    this.put("Ingenieria", "2");
	    this.put("Administrativo y Finanzas", "3");
	    this.put("Ciencias Sociales", "4");
	    this.put("Arte y Diseño", "5");
	    this.put("Leyes", "6");
	    this.put("Profesional en salud", "7");
	    this.put("Negocios", "8");
	    this.put("Industria", "9");
	    this.put("Independiente", "10");
	    this.put("Docencia", "11");
	}};
	
	private static Map<String, String> mapaHobbies = new HashMap<String,String>(){{
	    this.put("Ejercitarse", "1");
	    this.put("Ciclismo", "2");
	    this.put("Gaming", "3");
	    this.put("Trekking", "4");
	    this.put("Ver películas", "5");
	    this.put("Fotografía", "6");
	    this.put("Running", "7");
	    this.put("Estudiar", "8");
	    this.put("Deporte", "9");
	}};
	
	private static Map<String, String> mapaChildsYmedical= new HashMap<String,String>(){{
	    this.put("No", "0");
	    this.put("No:No", "0");
	    this.put("no", "0");
	    this.put("Si", "1");
	    this.put("Si:Si", "1");
	    this.put("Sí", "1");
	    this.put("Sí:Sí", "1");
	    this.put("si", "1");
	    this.put("sí", "1");
	}};
	
	public static InputDTOAzure convertIBMWatsonDataToAzure(IBMWatsonDTO data) {
		String age = Integer.toString(data.getAge());
		String gender = ConverterUtils.mapaGenero.get(data.getGender());
		String maritalState = ConverterUtils.mapaMaritalState.get(data.getMaritalState());
		String childs = ConverterUtils.mapaChildsYmedical.get(data.getChilds());
		String medicalState = ConverterUtils.mapaChildsYmedical.get(data.getMedicalState());
		String hobbie = ConverterUtils.mapaHobbies.get(data.getHobbie());
		String profession = ConverterUtils.mapaProfesion.get(data.getProfession());
		return new InputDTOAzure(age, gender, maritalState, childs, medicalState, hobbie, profession);
	}
}
