package mx.com.ms.utils;

/**
 * @project ms-employees
 * @created 25/02/2026
 * @author lvg
 */

import java.text.SimpleDateFormat;

public class ConstantsUtils {
	
    public static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("yyyy-MM-dd");
    public static final SimpleDateFormat DATE_TIME_FORMATTER = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    public static final String BR000 = "BR000";
    public static final String BR001 = "BR001";
    public static final String BR002 = "BR002";
    public static final String BR003 = "BR003";
    public static final String VR000 = "VR000";
    public static final String BR000_TXT = "Employee not found";
    public static final String BR001_TXT = "Employee not exist.";
    public static final String BR002_TXT = "The field status must be activo or inactivo"; 
    public static final String BR003_TXT = "birthDate must be a valid yyyy-MM-dd date";
    public static final String REGUEXP_STATUS_FORMAT = "activo|inactivo";


}
