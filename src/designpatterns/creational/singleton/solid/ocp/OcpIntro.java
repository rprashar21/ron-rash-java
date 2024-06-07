package designpatterns.creational.singleton.solid.ocp;

public class OcpIntro {

    // Open CLosed Principle
    // open for extension and closed for modification

    // new features should not modify existing code

    //it should allow to add a new feature without changing the existing behaviour
    // mostly we should create interfaces

    // Easily adding new features
    // less coding and developement
    // ocp requires decoupling which in turns follows single responsibilty principle


    // suppose I have a class NotificationService
    // need to send otp
    // need to send sometransactiondetails

    // create an interface with these methods and have different implementations which will send

}
interface NotificationService {

    void sendOtp();
    void sendTransactionReport();
}

class WhatsAppNotificationService implements NotificationService{


    @Override
    public void sendOtp() {
        // integrate with whatsApp api
    }

    @Override
    public void sendTransactionReport() {

    }
}

class TwitterNotificationService implements NotificationService{


    @Override
    public void sendOtp() {
   // integrate with twitter api
    }

    @Override
    public void sendTransactionReport() {

    }
}