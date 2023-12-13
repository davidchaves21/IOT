import { StyleSheet } from 'react-native';

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'space-between',
    backgroundColor: '#f0f0f0',
    padding: 10,
  },
  messagesContainer: {
    flex: 1,
  },
  botContainer: {
    alignItems: 'flex-end',
    marginTop: 10,
    marginBottom: 5,
  },
  userContainer: {
    alignItems: 'flex-start',
    marginTop: 10,
    marginBottom: 5,
  },
  botMessageBubble: {
    backgroundColor: '#F590A0',
    borderRadius: 5,
    padding: 10,
    maxWidth: '70%',
  },
  userMessageBubble: {
    backgroundColor: '#F4CDC8',
    borderRadius: 5,
    padding: 10,
    maxWidth: '70%',
  },
  messageText: {
    color: '#000',
    fontSize: 16,
  },
  inputContainer: {
    flexDirection: 'row',
    alignItems: 'center',
    backgroundColor: '#FFFFFF',
    padding: 5,
    borderRadius: 5,
  },
  input: {
    flex: 1,
    padding: 10,
    marginRight: 5,
    backgroundColor: '#FFFFFF',
    borderRadius: 5,
    borderWidth: 1,
    borderColor: '#CCCCCC',
    fontSize: 16,
  },
  sendButton: {
    width: 50,
    height: 50,
    backgroundColor: '#007AFF',
    borderRadius: 25,
    justifyContent: 'center',
    alignItems: 'center',
  },
});

  export default styles