describe('User Login Test', () => {
  it('should log in with valid credentials', () => {
    cy.visit('/login');
    cy.get('#username').type('validUsername');
    cy.get('#password').type('validPassword');
    cy.get('button[type=submit]').click();
    cy.url().should('include', '/dashboard');
    cy.contains('Welcome, validUsername').should('be.visible');
  });
});