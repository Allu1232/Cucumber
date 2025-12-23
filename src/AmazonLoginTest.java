cy.get('#username').type('validUser');
cy.get('#password').type('validPassword');
cy.get('#loginButton').click();
cy.url().should('include', '/dashboard');
cy.get('.welcome-message').should('contain', 'Welcome, validUser');