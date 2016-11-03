package collections.samples;

import java.util.PriorityQueue;

/**
 * @author yvesbeutler
 * This sample demonstrates a real world problem solved by the use of a Priority Queue.
 * Patients are ordered by their ID but if there are emergency cases, then this patients
 * have a higher priority than the others.
 */
public class PQTest {

    public static void main(String[] args) {

        // use Lambda expression to create the comparator
        PriorityQueue<Patient> priorityQueue = new PriorityQueue<>(10, (patient1, patient2) -> {
            if (patient1.emergency == patient2.emergency) {
                return Integer.valueOf(patient1.id).compareTo(patient2.id);
            } else {
                return patient1.emergency ? -1 : 1;
            }
        });

        priorityQueue.add(new Patient(1, "Sascha", false));
        priorityQueue.add(new Patient(2, "Mathew", false));
        priorityQueue.add(new Patient(3, "Joy", true));
        priorityQueue.add(new Patient(4, "Dario", false));
        priorityQueue.add(new Patient(5, "Jasmin", true));
        priorityQueue.add(new Patient(6, "Tobi", false));

        System.out.println("Doctor is waiting for his patients:");
        System.out.println();

        while (true) {
            Patient patient = priorityQueue.poll();
            if (patient != null) {
                System.out.print(patient.name + " <-- ");
            } else {
                break;
            }
        }

        System.out.println();
        System.out.println();
        System.out.println("Ladies first ^^");
    }
}

/**
 * Auxiliary class for patient
 */
class Patient {

    int id;
    String name;
    boolean emergency;

    Patient(int id, String name, boolean emergency) {
        this.id = id;
        this.name = name;
        this.emergency = emergency;
    }
}