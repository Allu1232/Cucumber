describe('User Login Test', () => {
  it('should log in with valid credentials', () => {
    cy.visit('https://example.com/login');
    cy.get('#username').type('testuser');
    cy.get('#password').type('password123');
    cy.get('button[type=submit]').click();
    cy.url().should('include', '/dashboard');
    cy.contains('Welcome, testuser').should('be.visible');
  });
});