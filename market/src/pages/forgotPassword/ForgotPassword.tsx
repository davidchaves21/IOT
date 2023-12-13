import React, { useState } from 'react'
import { Text, TextInput, ToastAndroid, View } from 'react-native'
import Icon from 'react-native-vector-icons/AntDesign';
import styles from './ForgotPasswordStyle';
import { Button } from 'react-native-elements';
import { useNavigation } from '@react-navigation/native';
import Login from '../login/Login';

const ForgotPassword = () => {
  const [email, setEmail] = useState('');
  const navigation = useNavigation();

  const handleForgotPassword = () => {
    const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (!emailPattern.test(email)) {
      ToastAndroid.show('Email inválido', ToastAndroid.SHORT);
      return;
    }else{
      ToastAndroid.show(String('EMAIL ENVIADO COM SUCESSO'), ToastAndroid.SHORT);
      navigation.navigate('login' as never); // 'login' as never apenas para parar de ficar o codigo vermelho
    }

    }
  return (
<View style={styles.container}>
      <View style={styles.logo}>
      <Icon name="rest" size={100} color={"red"}></Icon>
        <Text style={styles.logoName}> DRINKPLACE </Text>

      </View>
      
      <Text style={{textAlign:'center', fontSize:12}}> Ensira o seu email usado no cadastro para que possamos enviar um nova senha temporaria.</Text>
      <TextInput
        style={styles.input}
        placeholder=" Email"
        onChangeText={text => setEmail(text)}
        value={email}
      />
       <Text></Text>
      <Button title="RECUPERAR" buttonStyle={{ backgroundColor: '#FF999C' }} onPress={handleForgotPassword} />
       
      </View>
  )
}


export default ForgotPassword
