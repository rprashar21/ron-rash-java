package designpatterns.creational.singleton.solid.ocp.after.before;

public class HealthInsurance {

    public void giveDiscount(CustomerProfile customerProfile)
    {
        if(customerProfile.isLoyalCustomer())
        {
            // give discount
        }
    }
}
// now we have a feature to give discount to HealthInsurancePerson , tomorrow we may have to give it a vehicleinsurance cutomer
// so we will have to change the above method
// this class is not open for extension and if we change something we will modify the existing behaviour
// instead create an interface

interface CustomerProfile{

      boolean isLoyalCustomer();
}
class HealthInsurancePerson implements CustomerProfile{


    @Override
    public boolean isLoyalCustomer() {
        return false;
    }
}

class VehicleInsurancePerson implements CustomerProfile{


    @Override
    public boolean isLoyalCustomer() {
        return false;
    }
}
