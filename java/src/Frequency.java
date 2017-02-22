// Frequency.java
// Enumeration of frequencies
public enum Frequency {
	Annual(12),
	SemiAnnual(6),
	Quarterly(3),
	Monthly(1);
	
	private final int value;
	private Frequency(int value)
	{
		this.value = value;
	}
	public int getValue()
	{
		return value;
	}
}
