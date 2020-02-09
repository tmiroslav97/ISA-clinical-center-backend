import React from 'react';
import { Nav, Col, Row , Tab} from 'react-bootstrap';
import ClinicProfileInfo from "./ClinicsProfileInfo";

const ClinicProfile = () => {
    return(
        <Tab.Container id="left-tabs-doc-home" >
            <Row>
            <Col sm={3}>
            <Nav variant="pills" className="flex-column">
                
                
                
                <Nav.Item>
                <Nav.Link eventKey="fifth">Clinic info</Nav.Link>
                </Nav.Item>
            </Nav>
            </Col>
            <Col sm={9}>
            <Tab.Content>
                
                
                
                <Tab.Pane eventKey="fifth">
                    <ClinicProfileInfo/>
                </Tab.Pane>
            </Tab.Content>
            </Col>
        </Row>
        </Tab.Container>
    );
}

export default ClinicProfile;