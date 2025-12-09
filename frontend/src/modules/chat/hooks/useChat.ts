import { useState, useEffect, useCallback } from 'react';
import { ConversationDto, MessageDto } from '../types';
import ChatService from '../services/chat.service';
import WebSocketConnection from '../services/websocket.connection';

export const useChat = (userId: string) => {
  const [conversations, setConversations] = useState<ConversationDto[]>([]);
  const [selectedConversation, setSelectedConversation] = useState<ConversationDto | null>(null);
  const [messages, setMessages] = useState<MessageDto[]>([]);
  const [wsConnection, setWsConnection] = useState<WebSocketConnection | null>(null);

  // Carica tutte le conversazioni
  const loadConversations = useCallback(async () => {
    const convs = await ChatService.getConversations(userId);
    setConversations(convs);
  }, [userId]);

  // Seleziona conversazione
  const selectConversation = useCallback(async (conversationId: string) => {
    const conv = await ChatService.getConversation(conversationId);
    setSelectedConversation(conv);
    const msgs = await ChatService.getMessages(conversationId);
    setMessages(msgs);
  }, []);

  // Invia messaggio
  const sendMessage = useCallback(async (content: string) => {
    if (!selectedConversation) return;
    const message = await ChatService.sendMessage({
      content,
      conversationId: selectedConversation.id,
      senderId: userId,
    });
    setMessages(prev => [...prev, message]);
    wsConnection?.sendMessage(message);
  }, [selectedConversation, userId, wsConnection]);

  // Setup WebSocket
  useEffect(() => {
    const ws = new WebSocketConnection(userId);
    ws.onMessage((msg: MessageDto) => {
      if (msg.conversationId === selectedConversation?.id) {
        setMessages(prev => [...prev, msg]);
      }
    });
    setWsConnection(ws);

    return () => ws.close();
  }, [selectedConversation, userId]);

  // Caricamento iniziale conversazioni
  useEffect(() => {
    loadConversations();
  }, [loadConversations]);

  return {
    conversations,
    selectedConversation,
    messages,
    selectConversation,
    sendMessage,
    reloadConversations: loadConversations,
  };
};
