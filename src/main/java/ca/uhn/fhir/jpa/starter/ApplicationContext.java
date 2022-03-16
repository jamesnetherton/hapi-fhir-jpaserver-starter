package ca.uhn.fhir.jpa.starter;

import ca.uhn.fhir.context.FhirVersionEnum;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;

public class ApplicationContext extends AnnotationConfigWebApplicationContext {

    public ApplicationContext() {
        FhirVersionEnum fhirVersion = HapiProperties.getFhirVersion();
        if (fhirVersion == FhirVersionEnum.DSTU2) {
            register(FhirServerConfigDstu2.class, FhirServerConfigCommon.class);
        } else if (fhirVersion == FhirVersionEnum.DSTU2_HL7ORG) {
            register(FhirServerConfigDstu2Hl7Org.class, FhirServerConfigCommon.class);
        } else if (fhirVersion == FhirVersionEnum.DSTU2_1) {
            register(FhirServerConfigDstu2_1.class, FhirServerConfigCommon.class);
        } else if (fhirVersion == FhirVersionEnum.DSTU3) {
            register(FhirServerConfigDstu3.class, FhirServerConfigCommon.class);
        } else if (fhirVersion == FhirVersionEnum.R4) {
            register(FhirServerConfigR4.class, FhirServerConfigCommon.class);
        } else if (fhirVersion == FhirVersionEnum.R5) {
            register(FhirServerConfigR5.class, FhirServerConfigCommon.class);
        } else {
            throw new IllegalStateException();
        }

        if (HapiProperties.getSubscriptionWebsocketEnabled()) {
            register(ca.uhn.fhir.jpa.config.WebsocketDispatcherConfig.class);
        }

    }

}
