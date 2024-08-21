export const oktaConfig = {
    cliebtId: '0oaj336jlrX0G4mVj5d7',
    issuer: 'https://dev-70814966.okta.com/oauth2/default',
    redirectUri: 'http://localhost:3000/login/callback',
    scopes: ['openid', 'profile', 'email'],
    pkce: true,
    disableHttpsCheck: true,
}