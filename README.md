Endpoint authorization
=====================
This is a simple example of how to use the endpoint authorization feature of the spring security.

---

 Everything starts with authentication. 

 The authentication is done by the AuthenticationManager. 

 The AuthenticationManager is responsible for finding the right AuthenticationProvider. 

 The AuthenticationProvider is responsible for finding the right UserDetailsService. 

 The UserDetailsService is responsible for finding the right UserDetails. 

 The UserDetails is responsible for finding the right GrantedAuthority. 

 The GrantedAuthority is responsible for finding the right Permission.
 
---

Authentication [who you are?] (first filter) -> Authorization [based of who you are, grands you permissions] (second filter)
