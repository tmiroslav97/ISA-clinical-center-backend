import React from 'react';
import {Nav, Col, Row, Tab} from 'react-bootstrap';
import AvgClinicRating from './AvgClinicRating';
import AvgDoctorRating from './AvgDoctorRatng';
import Graphs from './Graphs';
import Incomes from './Incomes';

const BusinessReport = () => {
    return(
        <Tab.Container id="left-tabs-doc-home" >
            <Row>
            <Col sm={3}>
            <Nav variant="pills" className="flex-column">
                <Nav.Item>
                <Nav.Link eventKey="first">Average clinics rating</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                <Nav.Link eventKey="second">Average doctors rating</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                <Nav.Link eventKey="third">Graphs</Nav.Link>
                </Nav.Item>
                <Nav.Item>
                <Nav.Link eventKey="fourth">Incomes</Nav.Link>
                </Nav.Item>
            </Nav>
            </Col>
            <Col sm={9}>
            <Tab.Content>
                <Tab.Pane eventKey="first">
                    <AvgClinicRating/>
                </Tab.Pane>
                <Tab.Pane eventKey="second">
                    <AvgDoctorRating/>
                </Tab.Pane>
                <Tab.Pane eventKey="third">
                    <Graphs/>
                </Tab.Pane>
                <Tab.Pane eventKey="fourth">
                    <Incomes/>
                </Tab.Pane>
                </Tab.Content>
            </Col>
        </Row>
        </Tab.Container>
    );
}

export default BusinessReport;