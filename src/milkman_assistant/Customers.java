package milkman_assistant;

import java.sql.Date;

public class Customers 
{
	String cname;
	String mob;
	String adr;
	String loc;
	float cowq;
	float bufq;
	Date dos;
	
	public Customers(String cname, String mob, String adr, String loc, float cowq, float bufq, Date dos) 
	{
		super();
		this.cname = cname;
		this.mob = mob;
		this.adr = adr;
		this.loc = loc;
		this.cowq = cowq;
		this.bufq = bufq;
		this.dos = dos;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getMob() {
		return mob;
	}

	public void setMob(String mob) {
		this.mob = mob;
	}

	public String getAdr() {
		return adr;
	}

	public void setAdr(String adr) {
		this.adr = adr;
	}

	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public float getCowq() {
		return cowq;
	}

	public void setCowq(float cowq) {
		this.cowq = cowq;
	}

	public float getBufq() {
		return bufq;
	}

	public void setBufq(float bufq) {
		this.bufq = bufq;
	}

	public Date getDos() {
		return dos;
	}

	public void setDos(Date dos) {
		this.dos = dos;
	}
	
	
}
