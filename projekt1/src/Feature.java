public class Feature {
    String text;
    double number;

    public Feature(String text) {
        this.text = text;
        this.number = 0.0;
    }

    public Feature(double number) {
        this.text = null;
        this.number = number;
    }

    public double getNumber() {
        return number;
    }

    public String getText() {
        return text;
    }

    public void setNumber(double number) {
        this.number = number;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isNumber() {
        return this.text == null;
    }
}
