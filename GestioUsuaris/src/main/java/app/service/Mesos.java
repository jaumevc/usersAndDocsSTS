package app.service;

public enum Mesos {
	Gener(1), Febrer(2), Març(3), Abril(4), Maig(5), Juny(6), Juliol(7), Agost(8), Setembre(9), Octubre(10), Novembre(11), Desembre(12);
	
	private int value;

	Mesos(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
