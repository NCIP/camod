package gov.nih.nci.codegen.core.template.jet;

import java.util.*;
import org.omg.uml.foundation.core.*;
import org.omg.uml.modelmanagement.*;
import org.omg.uml.foundation.datatypes.*;
import gov.nih.nci.codegen.core.*;
import gov.nih.nci.codegen.core.transformer.*;
import gov.nih.nci.codegen.core.transformer.template.*;
import gov.nih.nci.codegen.core.filter.*;
import gov.nih.nci.codegen.core.handler.*;
import gov.nih.nci.codegen.core.access.*;
import gov.nih.nci.codegen.core.util.*;
import org.omg.uml.modelmanagement.UmlPackage;
import org.omg.uml.foundation.core.*;
import org.omg.uml.foundation.core.AssociationEnd;
import org.omg.uml.foundation.core.Attribute;
import org.omg.uml.foundation.core.Classifier;
import org.omg.uml.foundation.core.Dependency;
import org.omg.uml.foundation.core.ModelElement;
import org.omg.uml.foundation.core.Operation;
import org.omg.uml.foundation.core.Parameter;
import org.omg.uml.foundation.core.UmlAssociation;
import org.omg.uml.foundation.core.UmlClass;
import org.omg.uml.foundation.extensionmechanisms.Stereotype;
import org.omg.uml.foundation.extensionmechanisms.TaggedValue;
import org.omg.uml.modelmanagement.UmlPackage;

public class Bean implements JETTemplate{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = NL + NL + "package ";
  protected final String TEXT_3 = ";" + NL + "import ";
  protected final String TEXT_4 = ".*;" + NL + "import gov.nih.nci.system.applicationservice.*;" + NL + "import java.util.*;" + NL + "" + NL + "/**" + NL + " * <!-- LICENSE_TEXT_START -->" + NL + " * <!-- LICENSE_TEXT_END -->" + NL + " */" + NL + " ";
  protected final String TEXT_5 = NL;
  protected final String TEXT_6 = NL + "public ";
  protected final String TEXT_7 = " ";
  protected final String TEXT_8 = " class ";
  protected final String TEXT_9 = " ";
  protected final String TEXT_10 = NL + "    extends ";
  protected final String TEXT_11 = NL + "\timplements java.io.Serializable " + NL + "{" + NL + "\tprivate static final long serialVersionUID = 1234567890L;" + NL + "" + NL + "\t";
  protected final String TEXT_12 = NL + "\t   ";
  protected final String TEXT_13 = NL + "\t   ";
  protected final String TEXT_14 = " ";
  protected final String TEXT_15 = " ";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = " ";
  protected final String TEXT_18 = ";";
  protected final String TEXT_19 = NL + "\t   public ";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = " get";
  protected final String TEXT_22 = "(){" + NL + "\t      return ";
  protected final String TEXT_23 = ";" + NL + "\t   }" + NL + "\t   public void set";
  protected final String TEXT_24 = "(";
  protected final String TEXT_25 = " ";
  protected final String TEXT_26 = ")";
  protected final String TEXT_27 = " ";
  protected final String TEXT_28 = " ";
  protected final String TEXT_29 = ")";
  protected final String TEXT_30 = "{" + NL + "\t      this.";
  protected final String TEXT_31 = " = ";
  protected final String TEXT_32 = ";" + NL + "\t   }" + NL + "\t";
  protected final String TEXT_33 = NL + NL + "\t";
  protected final String TEXT_34 = NL + "\t   ";
  protected final String TEXT_35 = NL + "\t   ";
  protected final String TEXT_36 = NL + "\t   ";
  protected final String TEXT_37 = NL + "\t      ";
  protected final String TEXT_38 = NL + "\t\t\tprivate java.util.Collection ";
  protected final String TEXT_39 = " = new java.util.HashSet();" + NL + "\t\t\tpublic java.util.Collection get";
  protected final String TEXT_40 = "(){" + NL + "\t\t\ttry{" + NL + "\t\t\t   if(";
  protected final String TEXT_41 = ".size() == 0) {}" + NL + "\t\t           } catch(Exception e) {\t\t\t     " + NL + "\t\t\t      ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();" + NL + "\t\t\t      try {" + NL + "\t\t\t      " + NL + "\t\t\t      ";
  protected final String TEXT_42 = NL + "\t\t\t         ";
  protected final String TEXT_43 = NL + "\t\t\t         \t";
  protected final String TEXT_44 = ".";
  protected final String TEXT_45 = " thisIdSet = new ";
  protected final String TEXT_46 = ".";
  protected final String TEXT_47 = "();" + NL + "\t\t\t         \tthisIdSet.setId(this.getId()); " + NL + "\t\t\t         \t";
  protected final String TEXT_48 = " obj = new ";
  protected final String TEXT_49 = ".";
  protected final String TEXT_50 = "();" + NL + "\t\t\t\t \t";
  protected final String TEXT_51 = NL + "\t\t\t\t \t\tobj.setChild";
  protected final String TEXT_52 = "(thisIdSet);" + NL + "\t\t\t\t \t";
  protected final String TEXT_53 = NL + "\t\t\t\t \t        obj.setParent";
  protected final String TEXT_54 = "(thisIdSet);" + NL + "\t\t\t\t \t";
  protected final String TEXT_55 = NL + "\t\t\t\t \tjava.util.Collection resultList = applicationService.search(\"";
  protected final String TEXT_56 = "\", obj);\t\t\t\t " + NL + "\t\t\t\t \t" + NL + "\t\t\t\t \t";
  protected final String TEXT_57 = " = resultList;  " + NL + "\t\t\t\t \treturn resultList;" + NL + "\t\t\t         ";
  protected final String TEXT_58 = NL + "\t\t\t\t \t";
  protected final String TEXT_59 = ".";
  protected final String TEXT_60 = " thisIdSet = new ";
  protected final String TEXT_61 = ".";
  protected final String TEXT_62 = "();" + NL + "\t\t\t         \tthisIdSet.setId(this.getId());" + NL + "\t\t\t         \tjava.util.Collection resultList = applicationService.search(\"";
  protected final String TEXT_63 = "\", thisIdSet);\t\t\t\t " + NL + "\t\t\t\t \t";
  protected final String TEXT_64 = " = resultList;  " + NL + "\t\t\t\t \treturn resultList;" + NL + "\t\t\t\t ";
  protected final String TEXT_65 = NL + "\t\t\t      ";
  protected final String TEXT_66 = NL + "\t\t\t\t java.util.Collection resultList = applicationService.search(\"";
  protected final String TEXT_67 = "\", this);\t" + NL + "\t\t\t\t return resultList;\t " + NL + "\t\t\t\t ";
  protected final String TEXT_68 = NL + "\t\t\t      }catch(Exception ex) " + NL + "\t\t\t      {" + NL + "\t\t\t      \tSystem.out.println(\"";
  protected final String TEXT_69 = ":get";
  protected final String TEXT_70 = " throws exception ... ...\");" + NL + "\t\t\t   \t\tex.printStackTrace(); " + NL + "\t\t\t      }" + NL + "\t\t\t   }\t" + NL + "\t              return ";
  protected final String TEXT_71 = ";" + NL + "\t          }" + NL + "\t\t\t   " + NL + "\t\t\t   " + NL + "\t\t\t   " + NL + "\t\t\t   " + NL + "\t\t\t   " + NL + "\t      ";
  protected final String TEXT_72 = NL + "\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_73 = NL + "\t\t\t" + NL + "\t\t\t" + NL + "\t\t\tprivate ";
  protected final String TEXT_74 = " ";
  protected final String TEXT_75 = ";" + NL + "\t\t\tpublic ";
  protected final String TEXT_76 = " get";
  protected final String TEXT_77 = "(){" + NL + "\t\t\t" + NL + "\t\t\t";
  protected final String TEXT_78 = NL + "\t\t\t" + NL + "\t\t\t  return ";
  protected final String TEXT_79 = ";\t\t\t" + NL + "\t\t\t  " + NL + "\t\t\t";
  protected final String TEXT_80 = NL + "\t\t\t" + NL + "\t\t\t  ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();" + NL + "\t\t\t  ";
  protected final String TEXT_81 = ".";
  protected final String TEXT_82 = " thisIdSet = new ";
  protected final String TEXT_83 = ".";
  protected final String TEXT_84 = "();" + NL + "\t\t\t  thisIdSet.setId(this.getId());" + NL + "\t\t\t  ";
  protected final String TEXT_85 = NL + "\t\t\t   List relations = new ArrayList();" + NL + "\t\t\t   relations.add(thisIdSet);" + NL + "\t\t\t   ";
  protected final String TEXT_86 = " obj = new ";
  protected final String TEXT_87 = ".";
  protected final String TEXT_88 = "();";
  protected final String TEXT_89 = NL + "\t\t\t   \tobj.setChild";
  protected final String TEXT_90 = "Collection(relations);" + NL + "\t\t\t   ";
  protected final String TEXT_91 = NL + "\t\t\t   \tobj.setParent";
  protected final String TEXT_92 = "Collection(relations);" + NL + "\t\t\t   ";
  protected final String TEXT_93 = NL + "\t\t\t  try {" + NL + "\t\t\t  \tjava.util.List resultList = applicationService.search(\"";
  protected final String TEXT_94 = "\", obj);\t\t\t\t " + NL + "\t\t\t  \tif (resultList!=null && resultList.size()>0) {" + NL + "\t\t\t  \t";
  protected final String TEXT_95 = " = (";
  protected final String TEXT_96 = ")resultList.get(0);" + NL + "\t\t          }" + NL + "\t\t\t  ";
  protected final String TEXT_97 = NL + "\t\t\t  try {" + NL + "\t\t\t     java.util.List resultList = applicationService.search(\"";
  protected final String TEXT_98 = "\", thisIdSet);\t\t\t\t " + NL + "\t\t             if (resultList!=null && resultList.size()>0) {" + NL + "\t\t                ";
  protected final String TEXT_99 = " = (";
  protected final String TEXT_100 = ")resultList.get(0);" + NL + "\t\t             }" + NL + "\t\t          ";
  protected final String TEXT_101 = NL + "\t\t\t  } catch(Exception ex) " + NL + "\t\t\t  { " + NL + "\t\t\t      \tSystem.out.println(\"";
  protected final String TEXT_102 = ":get";
  protected final String TEXT_103 = " throws exception ... ...\");" + NL + "\t\t\t   \t\tex.printStackTrace(); " + NL + "\t\t\t  }" + NL + "\t\t\t  return ";
  protected final String TEXT_104 = ";\t" + NL + "\t\t\t " + NL + "\t\t\t ";
  protected final String TEXT_105 = "\t\t" + NL + "           }" + NL + "\t\t   " + NL + "\t      ";
  protected final String TEXT_106 = NL + "\t\t\t" + NL + "\t\t\t" + NL + "\t\t\tprivate ";
  protected final String TEXT_107 = " ";
  protected final String TEXT_108 = ";" + NL + "\t\t\tpublic ";
  protected final String TEXT_109 = " get";
  protected final String TEXT_110 = "(){" + NL + "\t\t\t";
  protected final String TEXT_111 = NL + "\t\t\t" + NL + "\t\t\t  return ";
  protected final String TEXT_112 = ";\t\t\t" + NL + "\t\t\t  " + NL + "\t\t\t";
  protected final String TEXT_113 = NL + "              ApplicationService applicationService = ApplicationServiceProvider.getApplicationService();" + NL + "\t\t\t  ";
  protected final String TEXT_114 = ".";
  protected final String TEXT_115 = " thisIdSet = new ";
  protected final String TEXT_116 = ".";
  protected final String TEXT_117 = "();" + NL + "\t\t\t  thisIdSet.setId(this.getId());" + NL + "\t\t\t  try {" + NL + "\t\t\t  java.util.List resultList = applicationService.search(\"";
  protected final String TEXT_118 = "\", thisIdSet);\t\t\t\t " + NL + "\t\t\t " + NL + "\t\t\t  if (resultList!=null && resultList.size()>0) {" + NL + "\t\t\t     ";
  protected final String TEXT_119 = " = (";
  protected final String TEXT_120 = ")resultList.get(0);" + NL + "\t\t\t     }" + NL + "\t\t\t  } catch(Exception ex) " + NL + "\t\t\t  { " + NL + "\t\t\t      \tSystem.out.println(\"";
  protected final String TEXT_121 = ":get";
  protected final String TEXT_122 = " throws exception ... ...\");" + NL + "\t\t\t   \t\tex.printStackTrace(); " + NL + "\t\t\t  }" + NL + "\t\t\t  return ";
  protected final String TEXT_123 = ";\t\t\t" + NL + "\t\t\t ";
  protected final String TEXT_124 = "\t\t" + NL + "              }" + NL + "                        " + NL + "\t      ";
  protected final String TEXT_125 = NL + "\t\t     ";
  protected final String TEXT_126 = NL + "\t      ";
  protected final String TEXT_127 = NL + "\t               " + NL + "\t   ";
  protected final String TEXT_128 = NL + "\t   \tpublic void set";
  protected final String TEXT_129 = "(java.util.Collection ";
  protected final String TEXT_130 = "){" + NL + "\t   \t\tthis.";
  protected final String TEXT_131 = " = ";
  protected final String TEXT_132 = ";" + NL + "\t        }\t" + NL + "\t   ";
  protected final String TEXT_133 = NL + "\t   " + NL + "\t   " + NL + "\t   public void set";
  protected final String TEXT_134 = "(";
  protected final String TEXT_135 = " ";
  protected final String TEXT_136 = "){" + NL + "\t\tthis.";
  protected final String TEXT_137 = " = ";
  protected final String TEXT_138 = ";" + NL + "\t   }\t" + NL + "\t   ";
  protected final String TEXT_139 = NL + "\t   ";
  protected final String TEXT_140 = NL + "\t      ";
  protected final String TEXT_141 = NL + "\t   private Long ";
  protected final String TEXT_142 = "Id;" + NL + "\t   public Long get";
  protected final String TEXT_143 = "Id(){" + NL + "\t      return ";
  protected final String TEXT_144 = "Id;" + NL + "\t   }" + NL + "\t      ";
  protected final String TEXT_145 = NL + "\t   ";
  protected final String TEXT_146 = NL + "\t";
  protected final String TEXT_147 = NL + NL + "\t\tpublic boolean equals(Object obj){" + NL + "\t\t\tboolean eq = false;" + NL + "\t\t\tif(obj instanceof ";
  protected final String TEXT_148 = ") {" + NL + "\t\t\t\t";
  protected final String TEXT_149 = " c =(";
  protected final String TEXT_150 = ")obj; \t\t\t " + NL + "\t\t\t\t";
  protected final String TEXT_151 = " thisId = getId();\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_152 = NL + "\t\t\t\t    if(thisId == c.getId()){" + NL + "\t\t\t\t       eq = true;" + NL + "\t\t\t\t    }" + NL + "\t\t\t\t ";
  protected final String TEXT_153 = NL + "\t\t\t\t\tif(thisId != null && thisId.equals(c.getId())) {" + NL + "\t\t\t\t\t   eq = true;" + NL + "\t\t\t\t    }\t\t" + NL + "\t\t\t\t";
  protected final String TEXT_154 = NL + "\t\t\t}" + NL + "\t\t\treturn eq;" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tpublic int hashCode(){" + NL + "\t\t\tint h = 0;" + NL + "\t\t\t";
  protected final String TEXT_155 = NL + "\t\t\t h += (new Long(getId())).hashCode();" + NL + "\t\t\t";
  protected final String TEXT_156 = NL + "\t\t\tif(getId() != null) {" + NL + "\t\t\t\th += getId().hashCode();" + NL + "\t\t\t}" + NL + "\t\t\t";
  protected final String TEXT_157 = NL + "\t\t\treturn h;" + NL + "\t}" + NL + "\t" + NL + "\t" + NL + "}";

        private String _omPkg, _dmPkg;
        private static Vector eagerFetchPackages = getEagerFetchPackages();
	public String capFirst(String s){
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}
	public String uncapFirst(String s){
		return s.substring(0, 1).toLowerCase() + s.substring(1);
	}
	private String getPackage(UmlClass klass) {
		        UmlPackage pkg = null;
		        pkg = UML13Utils.getModel(klass);
		        return UML13Utils.getNamespaceName(pkg, klass);
        }
	public String getGetterName(AssociationEnd ae){
		String name = ae.getName();
		if(name == null || name.trim().length() == 0){
			name = ae.getType().getName();
		}
		return "get" + capFirst(name);
	}
	public String getSetterName(AssociationEnd ae){
		String name = ae.getName();
		if(name == null || name.trim().length() == 0){
			name = ae.getType().getName();
		}
		return "set" + capFirst(name);
	}
	
	public boolean extendsOntology(Classifier klass) {
	boolean extendsOntology = false;
	TaggedValue ontologyTag = UML13Utils.getTaggedValue(klass,"implements-ontology");
	if (ontologyTag!=null) {
		if (ontologyTag.getValue().equals("yes")) {
	 	 extendsOntology=true;
		}
	}
	return extendsOntology;
	}
	
	public boolean extendsOntologyRelationship(Classifier klass) {
	boolean extendsOntologyRelationship = false;
		TaggedValue ontologyRelationshipTag = UML13Utils.getTaggedValue(klass,"implements-ontologyRelationship");
		if (ontologyRelationshipTag!=null) {
			if (ontologyRelationshipTag.getValue().equals("yes")) {
		 	 extendsOntologyRelationship=true;
			}
		}
		return extendsOntologyRelationship;
	}
	
	
	public String getQualifiedName(ModelElement me, String basePkg){

	String qName = null;
        UmlPackage pkg = null;
        if (basePkg != null) {
            pkg = UML13Utils.getPackage(UML13Utils.getModel(me), basePkg);
        } else {
            pkg = UML13Utils.getModel(me);
        }
        qName = UML13Utils.getNamespaceName(pkg, me) + "." + me.getName();
        return qName;		
	}
	
	public Attribute getIdAtt(UmlClass klass){
		Attribute idAtt = null;
		UmlClass superClass = klass;
		while(superClass != null && idAtt == null){
			idAtt = UML13Utils.getAttribute(superClass, "id");
			superClass = UML13Utils.getSuperClass(superClass);
		}
		return idAtt;
	}
	
	static private Vector getEagerFetchPackages()
	{
	    Vector vec = new Vector();
		try{
			Properties _properties = new Properties();
	
			_properties.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("CORESystem.properties"));

			String eagerPackages = (String)_properties.getProperty("eager_fetch_many2one_packages");
			StringTokenizer tokens = new StringTokenizer(eagerPackages, ",");
			while (tokens.hasMoreTokens())
			{
				String temp = tokens.nextToken().trim();
//				System.out.println("adding into property vector = " + temp);
				vec.add(temp);
			}
		}
    	catch(Exception ex){	
		    ex.printStackTrace();
			System.out.println("getProperties Exception - "+ ex.getMessage());
		}
    	
    	return vec;
	}
	
	public boolean isPrimitive(String typeName)
	{
		if (typeName.equals("int"))
		{
			return true;
		}
		if (typeName.equals("long"))
		{
			return true;
		}
		if (typeName.equals("double"))
		{
			return true;
		}		
		if (typeName.equals("float"))
		{
			return true;
		}		
		return false;
	}

	public String execute(Map context){
		return generate(context);
	}
	
	public String generate(Map context)
  {
    StringBuffer stringBuffer = new StringBuffer();
    stringBuffer.append(TEXT_1);
    
Classifier klass = (Classifier)context.get("modelElement");
String basePkg = (String)context.get("basePackage");
String basePkg1 = null;
String isAbstract = new String();

if (klass.isAbstract()) {
  isAbstract="abstract";
} 
if (isAbstract.equals("abstract")){
   
}

    stringBuffer.append(TEXT_2);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_3);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_4);
    
//import org.omg.uml.modelmanagement.UmlPackage;

    stringBuffer.append(TEXT_5);
    stringBuffer.append(UML13JavaSourceHelper.getClassJavadoc(klass));
    stringBuffer.append(TEXT_6);
     if (isAbstract.equals("abstract")) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(isAbstract);
    }
    stringBuffer.append(TEXT_8);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_9);
     UmlClass superClass = UML13Utils.getSuperClass((UmlClass)klass); 
     if(superClass != null){ 
    stringBuffer.append(TEXT_10);
    stringBuffer.append(superClass.getName());
     } 
    stringBuffer.append(TEXT_11);
     for(Iterator i = UML13Utils.getAttributes((UmlClass)klass).iterator(); i.hasNext();){ 
    stringBuffer.append(TEXT_12);
     Attribute att = (Attribute)i.next(); 
    stringBuffer.append(TEXT_13);
    stringBuffer.append(att.getVisibility().toString().substring(3));
    stringBuffer.append(TEXT_14);
     if(getQualifiedName(att.getType(), basePkg).startsWith(".")){
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_16);
    } else {
    stringBuffer.append(getQualifiedName(att.getType(), basePkg));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    if(getQualifiedName(att.getType(), basePkg).startsWith(".")){
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    } else { 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(getQualifiedName(att.getType(),basePkg));
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(capFirst(att.getName()));
    stringBuffer.append(TEXT_24);
     if(getQualifiedName(att.getType(), basePkg).startsWith(".")) {
    stringBuffer.append(getQualifiedName(att.getType(), basePkg).substring(1));
    stringBuffer.append(TEXT_25);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_26);
    } else {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(getQualifiedName(att.getType(), basePkg));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_29);
     } 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(att.getName());
    stringBuffer.append(TEXT_32);
     } 
    stringBuffer.append(TEXT_33);
     for(Iterator i = UML13Utils.getAssociationEnds(klass).iterator(); i.hasNext();){
    stringBuffer.append(TEXT_34);
     AssociationEnd thisEnd = (AssociationEnd)i.next(); 
    stringBuffer.append(TEXT_35);
     AssociationEnd otherEnd = (AssociationEnd)UML13Utils.getOtherAssociationEnd(thisEnd); 
    stringBuffer.append(TEXT_36);
     if(otherEnd.isNavigable()){ 
    stringBuffer.append(TEXT_37);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_38);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_41);
     if (!isAbstract.equals("abstract")) {
    stringBuffer.append(TEXT_42);
     if (extendsOntologyRelationship(otherEnd.getType())) { 
    stringBuffer.append(TEXT_43);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(otherEnd.getType()), basePkg), otherEnd.getType()));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(otherEnd.getType().getName());
    stringBuffer.append(TEXT_50);
    if(otherEnd.getName().startsWith("parent")) { 
    stringBuffer.append(TEXT_51);
    stringBuffer.append(thisEnd.getType().getName());
    stringBuffer.append(TEXT_52);
    } else {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(thisEnd.getType().getName());
    stringBuffer.append(TEXT_54);
     } 
    stringBuffer.append(TEXT_55);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_57);
     } else { 
    stringBuffer.append(TEXT_58);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_61);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_64);
     } 
    stringBuffer.append(TEXT_65);
     } else { 
    stringBuffer.append(TEXT_66);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_67);
     } 
    stringBuffer.append(TEXT_68);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_70);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_71);
     } else if( UML13Utils.isMany2One(thisEnd, otherEnd) ){ 
    stringBuffer.append(TEXT_72);
    
			String fullInterfaceName=getQualifiedName(otherEnd.getType(), basePkg);
		    String implName=fullInterfaceName.substring(fullInterfaceName.lastIndexOf(".")+1);
			String fullBeanName = fullInterfaceName;
			
    stringBuffer.append(TEXT_73);
    stringBuffer.append(fullInterfaceName);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_77);
     if (eagerFetchPackages.contains(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass))) {
    stringBuffer.append(TEXT_78);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_79);
     } else { 
    stringBuffer.append(TEXT_80);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_81);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_83);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_84);
     if(extendsOntology(otherEnd.getType()) && (extendsOntology(thisEnd.getType()) || extendsOntologyRelationship(thisEnd.getType()))) { 
    stringBuffer.append(TEXT_85);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(otherEnd.getType()), basePkg), otherEnd.getType()));
    stringBuffer.append(TEXT_87);
    stringBuffer.append(otherEnd.getType().getName());
    stringBuffer.append(TEXT_88);
    if(otherEnd.getName().startsWith("parent")) { 
    stringBuffer.append(TEXT_89);
    stringBuffer.append(capFirst(thisEnd.getType().getName()));
    stringBuffer.append(TEXT_90);
    } else {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(capFirst(thisEnd.getType().getName()));
    stringBuffer.append(TEXT_92);
     } 
    stringBuffer.append(TEXT_93);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_96);
     } else {
    stringBuffer.append(TEXT_97);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_98);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_100);
     } 
    stringBuffer.append(TEXT_101);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_104);
     } 
    stringBuffer.append(TEXT_105);
     }else if(UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    
			String fullInterfaceName=getQualifiedName(otherEnd.getType(), basePkg);
		        String implName=fullInterfaceName.substring(fullInterfaceName.lastIndexOf(".")+1);
			String fullBeanName = fullInterfaceName;
			
    stringBuffer.append(TEXT_106);
    stringBuffer.append(fullInterfaceName);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_109);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_110);
     if (eagerFetchPackages.contains(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass))) {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_112);
     } else { 
    stringBuffer.append(TEXT_113);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(UML13Utils.getNamespaceName(UML13Utils.getPackage(UML13Utils.getModel(klass), basePkg), klass));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(klass.getName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_123);
     } 
    stringBuffer.append(TEXT_124);
     }else{ 
    stringBuffer.append(TEXT_125);
     if(true) throw new RuntimeException("Unknown association multiplicity: " + thisEnd.getType().getName() + "." + thisEnd.getName() + ".upper=" + ((MultiplicityRange)thisEnd.getMultiplicity().getRange().iterator().next()).getUpper() + ", " + otherEnd.getType().getName() + "." + otherEnd.getName() + ".upper=" + ((MultiplicityRange)otherEnd.getMultiplicity().getRange().iterator().next()).getUpper()); 
    stringBuffer.append(TEXT_126);
     } 
    stringBuffer.append(TEXT_127);
     if(UML13Utils.isMany2Many(thisEnd, otherEnd) || UML13Utils.isOne2Many(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_128);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_132);
     } else if(UML13Utils.isMany2One(thisEnd, otherEnd) || UML13Utils.isOne2One(thisEnd, otherEnd)){ 
    stringBuffer.append(TEXT_133);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(getQualifiedName(otherEnd.getType(), basePkg));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_138);
     } 
    stringBuffer.append(TEXT_139);
     }else{//end if otherEnd.isNavigable() 
    stringBuffer.append(TEXT_140);
     if(otherEnd.getAggregation().equals(AggregationKindEnum.AK_AGGREGATE)){ 
    stringBuffer.append(TEXT_141);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(capFirst(otherEnd.getName()));
    stringBuffer.append(TEXT_143);
    stringBuffer.append(otherEnd.getName());
    stringBuffer.append(TEXT_144);
     } 
    stringBuffer.append(TEXT_145);
     } 
    stringBuffer.append(TEXT_146);
     } 
    stringBuffer.append(TEXT_147);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_148);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_149);
    stringBuffer.append(klass.getName() );
    stringBuffer.append(TEXT_150);
    stringBuffer.append(getIdAtt((UmlClass)klass).getType().getName());
    stringBuffer.append(TEXT_151);
    if (isPrimitive(getIdAtt((UmlClass)klass).getType().getName())) {
    stringBuffer.append(TEXT_152);
     } else { 
    stringBuffer.append(TEXT_153);
     } 
    stringBuffer.append(TEXT_154);
    if (isPrimitive(getIdAtt((UmlClass)klass).getType().getName())) {
    stringBuffer.append(TEXT_155);
     } else { 
    stringBuffer.append(TEXT_156);
     } 
    stringBuffer.append(TEXT_157);
    return stringBuffer.toString();
  }
}