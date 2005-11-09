/*
 * Created on Jun 17, 2005
 *
 * TODO To change the template for this generated file go to
 * Window - Preferences - Java - Code Style - Code Templates
 */
package gov.nih.nci.camod.service;

import gov.nih.nci.camod.domain.AnimalModel;
import gov.nih.nci.camod.domain.Image;
import gov.nih.nci.camod.webapp.form.ImageData;

import java.util.List;

public interface ImageManager {

    public List getAll() throws Exception;

    public Image get(String id) throws Exception;

    public void save(Image Image) throws Exception;

    public void remove(String id, AnimalModel inAnimalModel) throws Exception;

    public Image create(AnimalModel inAnimalModel, ImageData inImageData, String inPath, String inStorageDirKey) throws Exception;

    public void update(AnimalModel inAnimalModel, ImageData inImageData, Image inImage, String inPath, String inStorageDirKey) throws Exception;

}