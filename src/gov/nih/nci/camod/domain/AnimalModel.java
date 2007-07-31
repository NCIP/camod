/*
 * $Log: not supported by cvs2svn $
 * Revision 1.22  2007/03/19 18:56:11  pandyas
 * Object Model changes for caMOD 2.3 - dee design doc for details
 *
 * Revision 1.21  2007/02/23 21:30:01  pandyas
 * Fixed Genotype and Nomenclature - split objects and cleaned up database
 *
 * Revision 1.20  2006/10/17 16:14:36  pandyas
 * modified during development of caMOD 2.2 - various
 *
 * Revision 1.19  2006/05/03 20:02:29  pandyas
 * Modified to add Morpholino object data to application
 *
 * Revision 1.18  2006/04/17 19:13:46  pandyas
 * caMod 2.1 OM changes and added log/id header
 *
 * Revision 1.17  2006/01/18 14:23:31  georgeda
 * TT# 376 - Updated to use new Java 1.5 features
 *
 * Revision 1.16  2005/11/28 18:02:10  georgeda
 * Defect #182.  Get unique set of organs and only display metas. next to the originating organ
 *
 * Revision 1.15  2005/11/18 15:20:21  georgeda
 * Defect #120, gracefully handle the deletion of models.
 *
 * Revision 1.14  2005/11/14 14:16:51  georgeda
 * Cleanup
 *
 * 
 * $Id: AnimalModel.java,v 1.23 2007-07-31 12:03:37 pandyas Exp $
 */
package gov.nih.nci.camod.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class AnimalModel extends AbstractCancerModel {

	private static final long serialVersionUID = 4259665453799404851L;

	private String url;

	private Boolean isToolStrain;

	private String externalSource;

	private String externalSourceIdentifier;
    
    private String developmentalStage;    

	private Set<CellLine> cellLineCollection = new HashSet<CellLine>();

	private Set<Image> imageCollection = new HashSet<Image>();

	private Set<MicroArrayData> microArrayDataCollection = new HashSet<MicroArrayData>();

	private Set<Graft> graftCollection = new HashSet<Graft>();

	private Set<Therapy> therapyCollection = new HashSet<Therapy>();

	private Set<GeneDelivery> geneDeliveryCollection = new HashSet<GeneDelivery>();
	
	private Set<Nomenclature> nomenclatureCollection = new HashSet<Nomenclature>();	

	private Set<AnimalAvailability> animalAvailabilityCollection = new TreeSet<AnimalAvailability>();

	private Set<SpontaneousMutation> spontaneousMutationCollection = new TreeSet<SpontaneousMutation>();

	private Set<Histopathology> histopathologyCollection = new TreeSet<Histopathology>();

	private Set<EngineeredGene> engineeredGeneCollection = new TreeSet<EngineeredGene>();

	private Set<CarcinogenExposure> carcinogenExposureCollection = new HashSet<CarcinogenExposure>();

	private Set<TransientInterference> transientInterferenceCollection = new HashSet<TransientInterference>();
	
	private Set<Genotype> genotypeCollection = new HashSet<Genotype>();	

	private Set<Log> logCollection = new HashSet<Log>();

	private Phenotype phenotype;
	
	/**
	 * @return Returns the genotypeCollection.
	 */
	public Set<Genotype> getGenotypeCollection() {
		return genotypeCollection;
	}

	/**
	 * @param genotypeCollection
	 *            The genotypeCollection to set.
	 */
	public void setGenotypeCollection(
			Set<Genotype> genotypeCollection) {
		this.genotypeCollection = genotypeCollection;
	}

	/**
	 * @param genotypeCollection
	 *            The genotypeCollection to add.
	 */
	public void addGenotype(
			Genotype genotype) {
		genotypeCollection.add(genotype);
	} 
	
	/**
	 * @return Returns the nomenclatureCollection.
	 */
	public Set<Nomenclature> getNomenclatureCollection() {
		return nomenclatureCollection;
	}

	/**
	 * @param nomenclatureCollection
	 *            The nomenclatureCollection to set.
	 */
	public void setNomenclatureCollection(
			Set<Nomenclature> nomenclatureCollection) {
		this.nomenclatureCollection = nomenclatureCollection;
	}

	/**
	 * @param nomenclatureCollection
	 *            The nomenclatureCollection to add.
	 */
	public void addNomenclature(
			Nomenclature nomenclature) {
		nomenclatureCollection.add(nomenclature);
	}	

	/**
	 * @return Returns the engineeredGeneCollection.
	 */
	public Set<EngineeredGene> getEngineeredGeneCollection() {
		return engineeredGeneCollection;
	}

	/**
	 * @param engineeredGeneCollection
	 *            The engineeredGeneCollection to set.
	 */
	public void setEngineeredGeneCollection(
			Set<EngineeredGene> engineeredGeneCollection) {
		this.engineeredGeneCollection = engineeredGeneCollection;
	}

	/**
	 * @param engineeredGene
	 *            The engineeredGene to add.
	 */
	public void addEngineeredGene(EngineeredGene engineeredGene) {
		engineeredGeneCollection.add(engineeredGene);
	}

	/**
	 * @return Returns the carcinogenExposureCollection.
	 */
	public Set<CarcinogenExposure> getCarcinogenExposureCollection() {
		return carcinogenExposureCollection;
	}

	/**
	 * @param carcinogenExposureCollection
	 *            The carcinogenExposureCollection to set.
	 */
	public void setCarcinogenExposureCollection(
			Set<CarcinogenExposure> carcinogenExposureCollection) {
		this.carcinogenExposureCollection = carcinogenExposureCollection;
	}

	/**
	 * @param carcinogenExposureCollection
	 *            The carcinogenExposureCollection to add.
	 */
	public void addCarcinogenExposure(CarcinogenExposure carcinogenExposure) {
		carcinogenExposureCollection.add(carcinogenExposure);
	}

	/**
	 * @return Returns the transientInterferenceCollection.
	 */
	public Set<TransientInterference> getTransientInterferenceCollection() {
		return transientInterferenceCollection;
	}

	/**
	 * @param transientInterferenceCollection
	 *            The transientInterferenceCollection to set.
	 */
	public void setTransientInterferenceCollection(
			Set<TransientInterference> transientInterferenceCollection) {
		this.transientInterferenceCollection = transientInterferenceCollection;
	}

	/**
	 * @param transientInterferenceCollection
	 *            The transientInterferenceCollection to add.
	 */
	public void addTransientInterference(
			TransientInterference transientInterference) {
		transientInterferenceCollection.add(transientInterference);
	}

	/**
	 * @return Returns the spontaneousMutationCollection.
	 */
	public Set<SpontaneousMutation> getSpontaneousMutationCollection() {
		return spontaneousMutationCollection;
	}

	/**
	 * @param spontaneousMutationCollection
	 *            The spontaneousMutationCollection to set.
	 */
	public void setSpontaneousMutationCollection(
			Set<SpontaneousMutation> spontaneousMutationCollection) {
		this.spontaneousMutationCollection = spontaneousMutationCollection;
	}

	/**
	 * @param spontaneousMutation
	 *            The spontaneousMutation to add.
	 */
	public void addSpontaneousMutation(SpontaneousMutation spontaneousMutation) {
		spontaneousMutationCollection.add(spontaneousMutation);
	}

	/**
	 * @return Returns the histopathologyCollection.
	 */
	public Set<Histopathology> getHistopathologyCollection() {
		return histopathologyCollection;
	}

	/**
	 * @param histopathologyCollection
	 *            The histopathologyCollection to set.
	 */
	public void setHistopathologyCollection(
			Set<Histopathology> histopathologyCollection) {
		this.histopathologyCollection = histopathologyCollection;
	}

	/**
	 * @param histopathology
	 *            The histopathology to add.
	 */
	public void addHistopathology(Histopathology histopathology) {
		histopathologyCollection.add(histopathology);
	}


	/**
	 * @return Returns the animalAvailabilityCollection.
	 */
	public Set<AnimalAvailability> getAnimalAvailabilityCollection() {
		return animalAvailabilityCollection;
	}

	/**
	 * @param animalAvailabilityCollection
	 *            The animalAvailabilityCollection to set.
	 */
	public void setAnimalAvailabilityCollection(
			Set<AnimalAvailability> animalAvailabilityCollection) {
		this.animalAvailabilityCollection = animalAvailabilityCollection;
	}

	/**
	 * @param animalAvailability
	 *            The animalAvailability to add.
	 */
	public void addAnimalAvailability(AnimalAvailability animalAvailability) {
		animalAvailabilityCollection.add(animalAvailability);
	}

	/**
	 * @return Returns the geneDeliveryCollection.
	 */
	public Set<GeneDelivery> getGeneDeliveryCollection() {
		return geneDeliveryCollection;
	}

	/**
	 * @param geneDeliveryCollection
	 *            The geneDeliveryCollection to set.
	 */
	public void setGeneDeliveryCollection(
			Set<GeneDelivery> geneDeliveryCollection) {
		this.geneDeliveryCollection = geneDeliveryCollection;
	}

	/**
	 * @param geneDelivery
	 *            The geneDelivery to add.
	 */
	public void addGeneDelivery(GeneDelivery geneDelivery) {
		geneDeliveryCollection.add(geneDelivery);
	}

	/**
	 * @return Returns the isToolStrain.
	 */
	public Boolean getIsToolStrain() {
		return isToolStrain;
	}

	/**
	 * @param isToolStrain
	 *            The isToolStrain to set.
	 */
	public void setIsToolStrain(Boolean isToolStrain) {
		this.isToolStrain = isToolStrain;
	}

	/**
	 * @return Returns the externalSource.
	 */
	public String getExternalSource() {
		return externalSource;
	}

	/**
	 * @param externalSource
	 *            The externalSource to set.
	 */
	public void setExternalSource(String externalSource) {
		this.externalSource = externalSource;
	}

	/**
	 * @return Returns the externalSourceIdentifier.
	 */
	public String getExternalSourceIdentifier() {
		return externalSourceIdentifier;
	}

	/**
	 * @param externalSourceIdentifier
	 *            The externalSourceIdentifier to set.
	 */
	public void setExternalSourceIdentifier(String externalSourceIdentifier) {
		this.externalSourceIdentifier = externalSourceIdentifier;
	}
    
    /**
     * @return Returns the developmentalStage.
     */
    public String getDevelopmentalStage() {
        return developmentalStage;
    }

    /**
     * @param developmentalStage
     *            The developmentalStage to set.
     */
    public void setDevelopmentalStage(String developmentalStage) {
        this.developmentalStage = developmentalStage;
    }    

	/**
	 * @return Returns the phenotype.
	 */
	public Phenotype getPhenotype() {
		return phenotype;
	}

	/**
	 * @param phenotype
	 *            The phenotype to set.
	 */
	public void setPhenotype(Phenotype phenotype) {
		this.phenotype = phenotype;
	}

	/**
	 * @return Returns the therapyCollection.
	 */
	public Set<Therapy> getTherapyCollection() {
		return therapyCollection;
	}

	/**
	 * @param therapyCollection
	 *            The therapyCollection to set.
	 */
	public void setTherapyCollection(Set<Therapy> therapyCollection) {
		this.therapyCollection = therapyCollection;
	}

	/**
	 * @param therapy
	 *            The therapy to add.
	 */
	public void addTherapy(Therapy therapy) {
		therapyCollection.add(therapy);
	}

	/**
	 * @return Returns the graftCollection.
	 */
	public Set<Graft> getGraftCollection() {
		return graftCollection;
	}

	/**
	 * @param graftCollection
	 *            The graftCollection to set.
	 */
	public void setGraftCollection(Set<Graft> graftCollection) {
		this.graftCollection = graftCollection;
	}

	public void addGraft(Graft graft) {
		graftCollection.add(graft);
	}

	/**
	 * @return Returns the microArrayDataCollection.
	 */
	public Set<MicroArrayData> getMicroArrayDataCollection() {
		return microArrayDataCollection;
	}

	/**
	 * @param microArrayDataCollection
	 *            The microArrayDataCollection to set.
	 */
	public void setMicroArrayDataCollection(
			Set<MicroArrayData> microArrayDataCollection) {
		this.microArrayDataCollection = microArrayDataCollection;
	}

	/**
	 * @param microArrayData
	 *            The microArrayData to add.
	 */
	public void addMicroArrayData(MicroArrayData microArrayData) {
		microArrayDataCollection.add(microArrayData);
	}

	/**
	 * @return Returns the imageCollection.
	 */
	public Set<Image> getImageCollection() {
		return imageCollection;
	}

	/**
	 * @param imageCollection
	 *            The imageCollection to set.
	 */
	public void setImageCollection(Set<Image> imageCollection) {
		this.imageCollection = imageCollection;
	}

	/**
	 * @param image
	 *            The image to add.
	 */
	public void addImage(Image image) {
		imageCollection.add(image);
	}

	/**
	 * @return Returns the cellLineCollection.
	 */
	public Set<CellLine> getCellLineCollection() {
		return cellLineCollection;
	}

	/**
	 * @param cellLineCollection
	 *            The cellLineCollection to set.
	 */
	public void setCellLineCollection(Set<CellLine> cellLineCollection) {
		this.cellLineCollection = cellLineCollection;
	}

	/**
	 * @param cellLine
	 *            The cellLine to add.
	 */
	public void addCellLine(CellLine cellLine) {
		cellLineCollection.add(cellLine);
	}

	/**
	 * @return Returns the url.
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            The url to set.
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return Returns the logCollection.
	 */
	public Set<Log> getLogCollection() {
		return logCollection;
	}

	/**
	 * @param logCollection
	 *            The logCollection to set.
	 */
	public void setLogCollection(Set<Log> logCollection) {
		this.logCollection = logCollection;
	}

	/**
	 * @param log
	 *            The log to add.
	 */
	public void addLog(Log log) {
		logCollection.add(log);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String result = super.toString() + " - ";
		result += this.getUrl();
		return result;
	}

	public boolean equals(Object o) {
		if (!super.equals(o))
			return false;
		if (!(this.getClass().isInstance(o)))
			return false;
		return true;
	}

}
