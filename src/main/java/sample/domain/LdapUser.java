package sample.domain;

import java.io.Serializable;

import javax.naming.Name;

import org.springframework.ldap.odm.annotations.Attribute;
import org.springframework.ldap.odm.annotations.DnAttribute;
import org.springframework.ldap.odm.annotations.Entry;
import org.springframework.ldap.odm.annotations.Id;

@Entry(objectClasses = { "top", "uidObject", "person", "organizationalPerson" } , base="ou=users")
public class LdapUser implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 9081527761576640803L;

	@Id
	private Name    distinguisedName;
	@Attribute(name = "uid")
	@DnAttribute(value="uid", index=1)
	private String    uid;
	@Attribute(name = "cn")
	private String    cn;
	@Attribute(name = "sn")
	private String    sn;
	@Attribute(name = "postalAddress")
	private String    postalAddress;
	@Attribute(name = "telephoneNumber")
	private String    telephoneNumber;
	@Attribute(name = "userPassword")
	private String    userPassword;

	/**
	 * @return the uid
	 */
	public synchronized final String getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public synchronized final void setUid(String uid) {
		this.uid = uid;
	}

	/**
	 * @return the cn
	 */
	public synchronized final String getCn() {
		return cn;
	}

	/**
	 * @param cn
	 *            the cn to set
	 */
	public synchronized final void setCn(String cn) {
		this.cn = cn;
	}

	/**
	 * @return the sn
	 */
	public synchronized final String getSn() {
		return sn;
	}

	/**
	 * @param sn
	 *            the sn to set
	 */
	public synchronized final void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the userPassword
	 */
	public synchronized final String getUserPassword() {
		return userPassword;
	}

	/**
	 * @param userPassword
	 *            the userPassword to set
	 */
	public synchronized final void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	/**
	 * @return the postalAddress
	 */
	public synchronized final String getPostalAddress() {
		return postalAddress;
	}

	/**
	 * @param postalAddress
	 *            the postalAddress to set
	 */
	public synchronized final void setPostalAddress(String postalAddress) {
		this.postalAddress = postalAddress;
	}

	/**
	 * @return the telephoneNumber
	 */
	public synchronized final String getTelephoneNumber() {
		return telephoneNumber;
	}

	/**
	 * @param telephoneNumber
	 *            the telephoneNumber to set
	 */
	public synchronized final void setTelephoneNumber(String telephoneNumber) {
		this.telephoneNumber = telephoneNumber;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("User [");
		if (uid != null) {
			builder.append("uid=");
			builder.append(uid);
			builder.append(", ");
		}
		if (cn != null) {
			builder.append("cn=");
			builder.append(cn);
			builder.append(", ");
		}
		if (sn != null) {
			builder.append("sn=");
			builder.append(sn);
			builder.append(", ");
		}
		if (userPassword != null) {
			builder.append("userPassword=");
			builder.append(userPassword);
			builder.append(", ");
		}
		if (postalAddress != null) {
			builder.append("postalAddress=");
			builder.append(postalAddress);
			builder.append(", ");
		}
		if (telephoneNumber != null) {
			builder.append("telephoneNumber=");
			builder.append(telephoneNumber);
		}
		builder.append("]");
		return builder.toString();
	}
}
