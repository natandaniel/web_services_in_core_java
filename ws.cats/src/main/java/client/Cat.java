
package client;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Classe Java pour cat complex type.
 * 
 * <p>Le fragment de schéma suivant indique le contenu attendu figurant dans cette classe.
 * 
 * <pre>
 * &lt;complexType name="cat">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="avgAgeExpectancy" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="avgWeight" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="coatLength" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="colour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="grooming" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lifestyle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="size" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "cat", propOrder = {
    "avgAgeExpectancy",
    "avgWeight",
    "coatLength",
    "colour",
    "grooming",
    "lifestyle",
    "size"
})
public class Cat {

    protected int avgAgeExpectancy;
    protected int avgWeight;
    protected String coatLength;
    protected String colour;
    protected String grooming;
    protected String lifestyle;
    protected String size;

    /**
     * Obtient la valeur de la propriété avgAgeExpectancy.
     * 
     */
    public int getAvgAgeExpectancy() {
        return avgAgeExpectancy;
    }

    /**
     * Définit la valeur de la propriété avgAgeExpectancy.
     * 
     */
    public void setAvgAgeExpectancy(int value) {
        this.avgAgeExpectancy = value;
    }

    /**
     * Obtient la valeur de la propriété avgWeight.
     * 
     */
    public int getAvgWeight() {
        return avgWeight;
    }

    /**
     * Définit la valeur de la propriété avgWeight.
     * 
     */
    public void setAvgWeight(int value) {
        this.avgWeight = value;
    }

    /**
     * Obtient la valeur de la propriété coatLength.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCoatLength() {
        return coatLength;
    }

    /**
     * Définit la valeur de la propriété coatLength.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCoatLength(String value) {
        this.coatLength = value;
    }

    /**
     * Obtient la valeur de la propriété colour.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getColour() {
        return colour;
    }

    /**
     * Définit la valeur de la propriété colour.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setColour(String value) {
        this.colour = value;
    }

    /**
     * Obtient la valeur de la propriété grooming.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGrooming() {
        return grooming;
    }

    /**
     * Définit la valeur de la propriété grooming.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGrooming(String value) {
        this.grooming = value;
    }

    /**
     * Obtient la valeur de la propriété lifestyle.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLifestyle() {
        return lifestyle;
    }

    /**
     * Définit la valeur de la propriété lifestyle.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLifestyle(String value) {
        this.lifestyle = value;
    }

    /**
     * Obtient la valeur de la propriété size.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSize() {
        return size;
    }

    /**
     * Définit la valeur de la propriété size.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSize(String value) {
        this.size = value;
    }

	@Override
	public String toString() {
		return "Cat [avgAgeExpectancy=" + avgAgeExpectancy + ", avgWeight=" + avgWeight + ", coatLength=" + coatLength
				+ ", colour=" + colour + ", grooming=" + grooming + ", lifestyle=" + lifestyle + ", size=" + size + "]";
	}
}
