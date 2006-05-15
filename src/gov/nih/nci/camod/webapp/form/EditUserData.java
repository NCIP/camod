package gov.nih.nci.camod.webapp.form;


public interface EditUserData {

	public String getFirstName();

	public void setFirstName(String firstName);

	public String getLastName();

	public void setLastName(String lastName);

	public String getUsername();

	public void setUsername(String username);

	public String getId();

	public void setId(String id);

	public boolean isPrincipalInvestigator();

	public void setPrincipalInvestigator(boolean principalInvestigator);

    /**
     * @return Returns the affiliation.
     */
    public String getAffiliation();

    /**
     * @param affiliation
     *            The affiliation to set.
     */
    public void setAffiliation(String affiliation);

    /**
     * @return Returns the phoneNumber.
     */
    public String getPhone();

    /**
     * @param phoneNumber
     *            The phoneNumber to set.
     */
    public void setPhone(String phone);
}
