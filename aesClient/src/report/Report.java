package report;
/**
 * This class is an abstract class to represent a report.
 * The report has an id, average grade and median.
 */
public abstract class Report {
	private float avg;
	private float median;
	public Report(float avg, float median) {
		super();
		this.avg = avg;
		this.median = median;
	}

	public float getAvg() {
		return avg;
	}
	public void setAvg(float avg) {
		this.avg = avg;
	}
	public float getMedian() {
		return median;
	}
	public void setMedian(float median) {
		this.median = median;
	}
	
}
