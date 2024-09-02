export const oktaConfig = {
    clientId: '0oaj4qjuoj7l7aut95d7',
    issuer: 'https://dev-72913927.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}