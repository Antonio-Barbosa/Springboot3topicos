package br.gov.sp.fatec.springboot.Security;

import java.util.List;

public class Login {
    
        private String username;
        private String password;        
        private List<String> authotiries;        
        private String token;       
       

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public List<String> getAuthotiries() {
            return authotiries;
        }

        public void setAuthotiries(List<String> authotiries) {
            this.authotiries = authotiries;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        

}
