package com.etendoerp.workshop.ad_process;

import org.codehaus.jettison.json.JSONObject;
import org.hibernate.criterion.Restrictions;
import org.openbravo.client.application.process.BaseProcessActionHandler;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Location;


import java.util.List;
import java.util.Map;

public class SwapLocations extends BaseProcessActionHandler {

    @Override
    protected JSONObject doExecute(Map<String, Object> parameters, String content) {
        JSONObject jsonRequest = null;
        try {
            jsonRequest = new JSONObject(content);
            String partnerId = jsonRequest.getString("inpcBpartnerId");
            BusinessPartner businessPartner = OBDal.getInstance().get(BusinessPartner.class, partnerId);
            OBCriteria<Location> locationOBCriteria = OBDal.getInstance().createCriteria(Location.class);
            locationOBCriteria.add(Restrictions.eq(Location.PROPERTY_BUSINESSPARTNER+".id",partnerId));
            List<Location> locations = locationOBCriteria.list();
            for (Location location : locations) {
                location.setShipToAddress(!location.isShipToAddress());
                location.setInvoiceToAddress(!location.isInvoiceToAddress());
            }
            businessPartner.setDescription(businessPartner.getDescription()+" -- Modifique la descripcion");


        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return jsonRequest;
    }

}
