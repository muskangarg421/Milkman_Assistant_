package milkman_assistant;

import java.sql.Date;

public class BillClass 
{
	String cname;
	Date sdate;
	Date edate;
	float tcqty;
	float tbqty;
	float camt;
	float bamt;
	float total;
	Date pdate;
	public BillClass(String cname, Date sdate, Date edate, float tcqty, float tbqty, float camt, float bamt,
			float total, Date pdate) {
		super();
		this.cname = cname;
		this.sdate = sdate;
		this.edate = edate;
		this.tcqty = tcqty;
		this.tbqty = tbqty;
		this.camt = camt;
		this.bamt = bamt;
		this.total = total;
		this.pdate = pdate;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Date getSdate() {
		return sdate;
	}
	public void setSdate(Date sdate) {
		this.sdate = sdate;
	}
	public Date getEdate() {
		return edate;
	}
	public void setEdate(Date edate) {
		this.edate = edate;
	}
	public float getTcqty() {
		return tcqty;
	}
	public void setTcqty(float tcqty) {
		this.tcqty = tcqty;
	}
	public float getTbqty() {
		return tbqty;
	}
	public void setTbqty(float tbqty) {
		this.tbqty = tbqty;
	}
	public float getCamt() {
		return camt;
	}
	public void setCamt(float camt) {
		this.camt = camt;
	}
	public float getBamt() {
		return bamt;
	}
	public void setBamt(float bamt) {
		this.bamt = bamt;
	}
	public float getTotal() {
		return total;
	}
	public void setTotal(float total) {
		this.total = total;
	}
	public Date getPdate() {
		return pdate;
	}
	public void setPdate(Date pdate) {
		this.pdate = pdate;
	}
	
	
	
}
