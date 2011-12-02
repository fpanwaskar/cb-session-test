package com.burberry.apps.test;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.Hashtable;
import java.util.Set;

public class HttpSessionStorage {

    private final HttpSession session;
    private Hashtable<String, String> messages;

    private static final String SAML_STORAGE_KEY = "_springSamlStorageKey";

    /**
     * Creates the storage object and initializes it to load SAML messages from Session
     * found in the request object.
     *
     * @param request request to load/store messages from
     */
    public HttpSessionStorage(HttpServletRequest request) {
        this(request.getSession(true));
    }

    public HttpSessionStorage(HttpSession session) {
        this.session = session;
        this.messages = initializeStore();
    }

    /**
     * Call to the method tries to load messages hashtable object from the session, if the object doesn't exist
     * it will be created and stored.
     * <p/>
     * Method synchronizes on session object to prevent two threads from overwriting each others hashtable.
     *
     * @return found/created hashtable.
     */
    private Hashtable<String, String> initializeStore() {
        Hashtable<String, String> messages = (Hashtable<String, String>) session.getAttribute(SAML_STORAGE_KEY);
        if (messages == null) {
            synchronized (session) {
                messages = (Hashtable<String, String>) session.getAttribute(SAML_STORAGE_KEY);
                if (messages == null) {
                    messages = new Hashtable<String, String>();
                    session.setAttribute(SAML_STORAGE_KEY, messages);
                }
            }
        }
        return messages;
    }

    /**
     * Stores a request message into the repository. RequestAbstractType must have an ID
     * set. Any previous message with the same ID will be overwritten.
     *
     * @param id      ID of message
     * @param message message to be stored
     */
    public void storeMessage(String id, String message) {
        messages.put(id, message);
    }

    /**
     *
     * @param messageID ID of message to retrieve
     *
     * @return message found or null
     */
    public String retrieveMessage(String messageID) {
        String o = messages.get(messageID);
        if (o == null) {
            return null;
        } else {
            return o;
        }
    }

    public Set<String> getAllMessages() {
        return Collections.unmodifiableSet(messages.keySet());
    }
}
