import React from 'react';
import { Container } from 'react-bootstrap';
 
function Footer(){
    return(
		<Container className="mt-5">
			<footer className="fixed-bottom">
				<nav className="navbar navbar-expand-sm bg-light navbar-light">
					<div className="container">
						<div className="navbar-text pull-left">
							<p>© 2019 Jovana, Nevena i Miroslav</p>
						</div>
					</div>
				</nav>
			</footer>
		</Container>
    );
}
 
export default Footer;