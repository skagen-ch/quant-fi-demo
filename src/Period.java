// Period.java
// Basic class to represent a time period with a start and end date
import java.util.Date;

public class Period {
	private Date _startDate;
	private Date _endDate;
	
	public Period()
	{
	}
	
	public Period(Date startDate, Date endDate)
	{
		_startDate = startDate;
		_endDate = endDate;
	}

	public void setStartDate(Date startDate)
    {
    	_startDate = startDate;
	}
    
    public Date getStartDate()
    {
    	return _startDate;
	}

    public void setEndDate(Date endDate)
    {
    	_endDate = endDate;
	}
    
    public Date getEndDate()
    {
    	return _endDate;
	}
}
