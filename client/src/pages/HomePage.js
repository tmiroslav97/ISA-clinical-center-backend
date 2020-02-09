import React from 'react';
import { Row, Col, Container, Carousel} from 'react-bootstrap';


function HomePage() {
    return (
        <Container>
            <Row>
                <Col md={{ span:10, offset: 1 }} xs={12}>
                    <Carousel>
                        <Carousel.Item>
                            <img
                            className="d-block w-100"
                            src="img/home_img.jpg"
                            alt="First slide"
                            />
                            <Carousel.Caption>
                            <h3>Welecome to Clinic Center System!</h3>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img
                            className="d-block w-100"
                            src="img/dom_zdravlja.jpg"
                            alt="Second slide"
                            />

                            <Carousel.Caption>
                            <h3>Our community health centre</h3>
                            </Carousel.Caption>
                        </Carousel.Item>
                        <Carousel.Item>
                            <img
                            className="d-block w-100"
                            src="img/hitna_pomoc.jpg"
                            alt="Third slide"
                            />

                            <Carousel.Caption>
                            <h3>Our ambulance</h3>
                            </Carousel.Caption>
                        </Carousel.Item>                
                    </Carousel>
                    
                </Col>
            </Row>
        </Container>
    );
  }
  
  export default HomePage;