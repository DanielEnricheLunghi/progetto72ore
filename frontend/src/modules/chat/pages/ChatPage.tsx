import React, { useState } from 'react';
import ChatSidebar from '../components/ChatSidebar';
import ConversationPage from './ConversationPage';
import { useChat } from '../hooks/useChat';

interface Props {
  userId: string;
}

const ChatPage: React.FC<Props> = ({ userId }) => {
  const {
    conversations,
    selectedConversation,
    selectConversation,
    messages,
    sendMessage,
  } = useChat(userId);

  const [currentConversationId, setCurrentConversationId] = useState<string | undefined>(undefined);

  const handleSelectConversation = (id: string) => {
    selectConversation(id);
    setCurrentConversationId(id);
  };

  return (
    <div className="flex h-screen">
      <ChatSidebar
        conversations={conversations}
        onSelectConversation={handleSelectConversation}
        selectedConversationId={currentConversationId}
      />

      {selectedConversation ? (
        <ConversationPage
          conversation={selectedConversation}
          messages={messages}
          onSend={sendMessage}
        />
      ) : (
        <div className="flex-1 flex items-center justify-center text-gray-400">
          Seleziona una conversazione
        </div>
      )}
    </div>
  );
};

export default ChatPage;
