package datastructures.stacks.questions;

import java.util.Arrays;
import java.util.Stack;

class Car{
    public int position;
    public int speed;

    public Car(final int position, final int speed) {
        this.position = position;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return "Car{" +
                "position=" + position +
                ", speed=" + speed +
                '}';
    }
}

public class CarFleetBetterSolution {
    public static int carFleet(int target, int[] position, int[] speed) {
       // create a Pair of the position and speed
        int length = position.length;
        Car[] arrayOfCars = new Car[length];
        for(int i=0;i< length;i++){
            Car car = new Car(position[i],speed[i]);
            arrayOfCars[i]=car;
        }
        // sort the cars based based on the psoition
        //{}
       Arrays.sort(arrayOfCars,(car1,car2)->Integer.compare(car1.position,car2.position));
        System.out.println(Arrays.toString(arrayOfCars));
        // time taken by each car to reach destination
        // will the other car behind that catch it if the speed is more only then
        // if it catchehs it forms a fleet and then the speed will remain constant

        Stack<Integer> stack = new Stack<>();
        int fleet=1;
        for(int i=arrayOfCars.length-1 ;i>=0;i--)
        {
            int timeToReach = (target - arrayOfCars[i].position)/arrayOfCars[i].speed;
            stack.push(timeToReach);

            if(stack.size()>=2)
            {
                int car2Time = stack.pop();
                int car1Time = stack.peek();

                if(car2Time<=car1Time){
                    fleet++;
                }
                else {
                    stack.push(car2Time);
                }
            }

        }
        System.out.println(fleet);
        return fleet;
    }

    public static void main(String[] args) {
        int target = 12;
        int[] position = {10, 8, 0, 5, 3};
        int[] speed = {2, 4, 1, 1, 3};

        int result = carFleet(target, position, speed);
        System.out.println("Number of car fleets: " + result);
    }
}
