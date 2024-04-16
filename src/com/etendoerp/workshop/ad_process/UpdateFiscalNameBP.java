package com.etendoerp.workshop.ad_process;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.openbravo.dal.service.OBCriteria;
import org.openbravo.dal.service.OBDal;
import org.openbravo.model.common.businesspartner.BusinessPartner;
import org.openbravo.model.common.businesspartner.Category;
import org.openbravo.model.common.enterprise.Organization;
import org.openbravo.scheduling.ProcessBundle;
import org.openbravo.service.db.DalBaseProcess;

import java.util.LinkedList;
import java.util.List;

public class UpdateFiscalNameBP extends DalBaseProcess {

    @Override
    protected void doExecute(ProcessBundle bundle) throws Exception {

//        final OBCriteria<BusinessPartner> businessPartnerOBCriteria = OBDal.getInstance().createCriteria(BusinessPartner.class);
//        // IsActive= 'Y'
//        // Cliente actual / legible  --> System no tiene BPs
//        // Organización actual / legible
//
//        final OBCriteria<Organization> orgOBCriteria = OBDal.getInstance().createCriteria(Organization.class);
//        //orgOBCriteria.createAlias(Organization.PROPERTY_NAME, "name");
//        //orgOBCriteria.add(Restrictions.eq("name", "España"));
//        orgOBCriteria.add(Restrictions.ilike(Organization.PROPERTY_NAME, "%Espa%"));
//        List<Organization> listOrgs = orgOBCriteria.list();
//        final OBCriteria<Category> catOBCriteria = OBDal.getInstance().createCriteria(Category.class);
//        catOBCriteria.add(Restrictions.ilike(Category.PROPERTY_NAME,"%Employee%"));
//        Category catEmployee = (Category) catOBCriteria.uniqueResult();
//
//        List<String> orgIds = new LinkedList<>();
//        for (Organization org : listOrgs) {
//            orgIds.add(org.getId());
//        }
//
//        businessPartnerOBCriteria.add(Restrictions.in(BusinessPartner.PROPERTY_ORGANIZATION+".id",orgIds));
//        businessPartnerOBCriteria.add(Restrictions.eq(BusinessPartner.PROPERTY_BUSINESSPARTNERCATEGORY+".id",catEmployee.getId()));
//
//
//        List<BusinessPartner> businessPartnerList = businessPartnerOBCriteria.list();
//
//        for (BusinessPartner businessPartner : businessPartnerList) {
//            businessPartner.setName2(businessPartner.getName()+" - "+businessPartner.getSearchKey());
//        }

        OBDal.getInstance().getSession().createQuery("UPDATE BusinessPartner bp SET bp.name2 = bp.name || ' - ' || bp.searchKey WHERE bp.id IN (SELECT bp1.id FROM BusinessPartner bp1 WHERE bp1.organization.name like '%Espa%' and bp1.businessPartnerCategory.name like '%Employee%')").executeUpdate();
    }

}
