import React, { useState, useEffect } from 'react';
import { ScrollView, View, Text, TextInput, TouchableOpacity, KeyboardAvoidingView, Platform } from 'react-native';
import { styles } from './ChatStyle';
import Icon from 'react-native-vector-icons/AntDesign';

const Chat = () => {
  const botMessages = [
    'Olá! Como posso ajudar?',
    'Estou aqui para tirar suas dúvidas.',
    'Acho que não estou muito afim de ajudar hoje não, quer tentar amanhã?',
    'Não to com bom humor em!!',
    'Ai ai cansei, beijos :*',

  ];

  const [userMessage, setUserMessage] = useState('');
  const [messages, setMessages] = useState<string[]>([]);
  const [vezDoBot, setVezDoBot] = useState(true);
  const [vezDoUsuario, setVezDoUsuario] = useState(false);
  const [botIndex, setBotIndex] = useState(0);

  useEffect(() => {
    if (vezDoBot) {
      if (botIndex < botMessages.length) {
        setMessages([...messages, botMessages[botIndex]]);
        setBotIndex(botIndex + 1);
        setVezDoBot(false);
        setVezDoUsuario(true);
      } else {
        setVezDoUsuario(false);
      }
    }
  }, [vezDoBot]);

  const handleSendMessage = () => {
    if (userMessage.trim().length > 0 && vezDoUsuario) {
      setMessages([...messages, userMessage.trim()]);
      setUserMessage('');
      setVezDoBot(true);
      setVezDoUsuario(false);
    }
  };

  const renderMessages = () => {
    return messages.map((message, index) => {
      const isBotMessage = index % 2 === 0; // Verifica se é uma mensagem do bot

      return (
        <View key={index} style={isBotMessage ? styles.botContainer : styles.userContainer}>
          <View style={isBotMessage ? styles.botMessageBubble : styles.userMessageBubble}>
            <Text style={styles.messageText}>{message}</Text>
          </View>
        </View>
      );
    });
  };

  const isLastBotMessage = botIndex === botMessages.length;

  return (
    <KeyboardAvoidingView
      style={{ flex: 1 }}
      behavior={Platform.OS === 'ios' ? 'padding' : undefined}
      keyboardVerticalOffset={Platform.OS === 'ios' ? 0 : 10}
    >
      <View style={styles.container}>
        <ScrollView contentContainerStyle={styles.messagesContainer}>
          {renderMessages()}
        </ScrollView>

        {!isLastBotMessage && ( // Verifica se não é a última mensagem do bot
          <View style={styles.inputContainer}>
            <TextInput
              style={styles.input}
              placeholder="Digite sua mensagem..."
              value={userMessage}
              onChangeText={(text) => setUserMessage(text)}
              editable={vezDoUsuario}
            />
            <TouchableOpacity
              style={styles.sendButton}
              onPress={handleSendMessage}
              disabled={!vezDoUsuario}
            >
              <Icon name="right" size={25} color="#FFFFFF" />
            </TouchableOpacity>
          </View>
        )}
      </View>
    </KeyboardAvoidingView>
  );
};

export default Chat;
