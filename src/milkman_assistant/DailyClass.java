package milkman_assistant;

import java.sql.Date;

public class DailyClass 
{
	String cname;
	Date cdate;
	float cq;
	float bq;
	public DailyClass(String cname, Date cdate, float cq, float bq) {
		super();
		this.cname = cname;
		this.cdate = cdate;
		this.cq = cq;
		this.bq = bq;
	}
	
	public DailyClass(String cname, float cq, float bq) {
		super();
		this.cname = cname;
		this.cq = cq;
		this.bq = bq;
	}

	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public Date getCdate() {
		return cdate;
	}
	public void setCdate(Date cdate) {
		this.cdate = cdate;
	}
	public float getCq() {
		return cq;
	}
	public void setCq(float cq) {
		this.cq = cq;
	}
	public float getBq() {
		return bq;
	}
	public void setBq(float bq) {
		this.bq = bq;
	}
	
}
