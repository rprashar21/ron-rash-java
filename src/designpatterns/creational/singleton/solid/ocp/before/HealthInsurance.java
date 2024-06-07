package designpatterns.creational.singleton.solid.ocp.before;

public class HealthInsurance {

    public void giveDiscount(HealthInsurancePerson healthInsurancePerson)
    {
        if(healthInsurancePerson.isLoyalCustomer())
        {
            // give discount
        }
    }
}
// now we have a feature to give discount to HealthInsurancePerson , tomorrow we may have to give it a vehicleinsurance cutomer
// so we will have to change the above method
// this class is not open for extension and if we change something we will modify the existing behaviour

class HealthInsurancePerson{

    private boolean isLoyalCustomer;

    public boolean isLoyalCustomer() {
        return isLoyalCustomer;
    }

    public void setLoyalCustomer(final boolean loyalCustomer) {
        isLoyalCustomer = loyalCustomer;
    }
}

class VehicleInsurancePerson{

    private boolean isLoyalCustomer;

    public boolean isLoyalCustomer() {
        return isLoyalCustomer;
    }

    public void setLoyalCustomer(final boolean loyalCustomer) {
        isLoyalCustomer = loyalCustomer;
    }
}