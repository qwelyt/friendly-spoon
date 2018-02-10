package queue.rabbitmq;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import javax.net.ssl.KeyManagerFactory;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManagerFactory;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.channels.AlreadyConnectedException;
import java.security.*;
import java.security.cert.CertificateException;
import java.util.concurrent.TimeoutException;

public final class RabbitMqConnectionService {
    private static Connection instance = null;

    public static Connection instance(final String host, final int port, final String user, final String password, final String virtualHost) throws IOException, TimeoutException {
        if (instance == null) {
            final ConnectionFactory factory = basicFactorySettings(host, port, user, password, virtualHost);
            instance = factory.newConnection();
            return instance;
        } else {
            throw new AlreadyConnectedException();
        }
    }

    public static Connection instanceSSL(final String host, final int port, final String user, final String password, final String virtualHost, final String keyPass, final String certFormat, final String pathToCert, final String rabbitTrustStorePassword, final String pathToRabbitTrustStore, final String tlsVersion) throws IOException, TimeoutException, KeyManagementException, NoSuchAlgorithmException, CertificateException, KeyStoreException, UnrecoverableKeyException {
        if (instance == null) {
            final ConnectionFactory factory = basicFactorySettings(host, port, user, password, virtualHost);
            setupSSL(factory, keyPass, certFormat, pathToCert, rabbitTrustStorePassword, pathToRabbitTrustStore, tlsVersion);
            instance = factory.newConnection();
            return instance;
        } else {
            throw new AlreadyConnectedException();
        }
    }

    public static Connection instance() {
        return instance;
    }

    private static ConnectionFactory basicFactorySettings(final String host, final int port, final String user, final String password, final String virtualHost) {
        final ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(host);
        factory.setPort(port);
        factory.setUsername(user);
        factory.setPassword(password);
        factory.setVirtualHost(virtualHost);
        factory.setAutomaticRecoveryEnabled(true);
        return factory;
    }

    private static void setupSSL(final ConnectionFactory factory, final String keyPass, final String certFormat, final String pathToCert, final String rabbitTrustStorePassword, final String pathToRabbitTrustStore, final String tlsVersion) throws KeyManagementException, NoSuchAlgorithmException, KeyStoreException, IOException, CertificateException, UnrecoverableKeyException {
        char[] keyPassphrase = keyPass.toCharArray();
        KeyStore ks = KeyStore.getInstance(certFormat);
        ks.load(new FileInputStream(pathToCert), keyPassphrase);

        KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");
        kmf.init(ks, keyPassphrase);

        char[] trustPassphrase = rabbitTrustStorePassword.toCharArray();
        KeyStore tks = KeyStore.getInstance("JKS");
        tks.load(new FileInputStream(pathToRabbitTrustStore), trustPassphrase);

        TrustManagerFactory tmf = TrustManagerFactory.getInstance("SunX509");
        tmf.init(tks);

        SSLContext c = SSLContext.getInstance(tlsVersion);
        c.init(kmf.getKeyManagers(), tmf.getTrustManagers(), null);

        factory.useSslProtocol(c);
    }
}
