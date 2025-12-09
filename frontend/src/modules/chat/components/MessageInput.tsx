import React, { useState } from 'react';

interface Props {
  onSend: (message: string) => void;
}

const MessageInput: React.FC<Props> = ({ onSend }) => {
  const [message, setMessage] = useState('');

  const handleSend = () => {
    if (message.trim()) {
      onSend(message);
      setMessage('');
    }
  };

  return (
    <div className="p-4 border-t flex">
      <input
        type="text"
        value={message}
        onChange={e => setMessage(e.target.value)}
        className="flex-1 border rounded p-2"
        placeholder="Type a message..."
        onKeyDown={e => e.key === 'Enter' && handleSend()}
      />
      <button onClick={handleSend} className="ml-2 px-4 py-2 bg-blue-600 text-white rounded">Send</button>
    </div>
  );
};

export default MessageInput;

