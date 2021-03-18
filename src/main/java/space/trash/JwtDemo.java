package space.trash;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.InvalidClaimException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @briefing token demo
 * @author unknown
 * @date 2020/10/10 21:13
 */
public class JwtDemo {
    /**
     * 1、选择签名的算法
     * 2、生成签名的密钥
     * 3、构建Token信息
     * 4、利用算法和密钥生成Token
     */
    public static String createToken() {
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        byte[] secretBytes = DatatypeConverter.parseBase64Binary("JWT-TOKEN");//字节数组
        Key signingKey = new SecretKeySpec(secretBytes, signatureAlgorithm.getJcaName());//根据字节数组生成HS256密钥
        Map<String, Object> claims = new HashMap<String, Object>();
        claims.put("username", "token");
        claims.put("role", "admin");
        JwtBuilder builder = Jwts.builder()
//                .setHeader()  //header可以直接省略，使用默认的
                .setClaims(claims)
                .setId("tokenid") //即jti
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+10*60*1000)) //设置过期时间为10min
                .signWith(signatureAlgorithm, signingKey); // TODO: 2020/11/18 ??? key
        String compact = builder.compact();
        System.out.println(compact);//这个就是生成的token
        return compact;
    }

    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary("JWT-TOKEN"))//提供签名
                .parseClaimsJws(token)
                .getBody();
    }

    public static void validateToken(String token) {
        try{
            Claims claims = parseToken(token);
            String username = claims.get("username").toString();
            String role = claims.get("role").toString();
            String tokenid = claims.getId();
            System.out.println(claims.getExpiration());
            System.out.println("[username]:"+username);
            System.out.println("[role]:"+role);
            System.out.println("[tokenid]:"+tokenid);
        } catch(ExpiredJwtException e) {
            System.out.println("token expired");
        } catch (InvalidClaimException e) {
            System.out.println("token invalid");
        } catch (Exception e) {
            System.out.println("token error");
        }
    }

    public static void main(String[] args) {
        validateToken(createToken());
    }

}

